(ns turing_machine.core_test
  (:use [turing_machine.fixtures])
  (:use [turing_machine.core])
  (:use [turing_machine.tape])
  (:use [turing_machine.m_config])
  (:use [clojure.test])
)

(deftest should_create_machine_with_configurations
	(def machine (:machine test_machine_complete_config))
)

(deftest should_execute_single_step_according_to_configurations
	(def initial_complete_config (test_machine_complete_config))
	(def machine (:machine initial_complete_config))
	(def initial_tape (:tape initial_complete_config))
	
	(def complete_config_after_first_step (execute_single_step machine initial_tape))
	(def machine_after_first_step (:machine complete_config_after_first_step))
	(def tape_after_first_step (:tape complete_config_after_first_step))
	(print_tape tape_after_first_step 10)
	
	(is (= "0" (read_square tape_after_first_step 0)))
	(is (= "" (read_square tape_after_first_step 1)))
	(is (= "" (read_square tape_after_first_step 2)))
	
	(def complete_config_after_second_step (execute_single_step machine_after_first_step tape_after_first_step))
	(def machine_after_second_step (:machine complete_config_after_second_step))
	(def tape_after_second_step (:tape complete_config_after_second_step))
	(print_tape tape_after_second_step 10)

	(is (= "0" (read_square tape_after_second_step 0)))
	(is (= "1" (read_square tape_after_second_step 1)))
	(is (= "" (read_square tape_after_second_step 2)))
)

(deftest should_execute_continuously
	(def initial_complete_config (test_machine_complete_config))
	(def machine (:machine initial_complete_config))
	(def initial_tape (:tape initial_complete_config))
	
	(def complete_config_after_execute (execute machine initial_tape 5))
	(def tape_after_execute (:tape complete_config_after_execute))
	
	(is (= "0" (read_square tape_after_execute 0)))
	(is (= "1" (read_square tape_after_execute 1)))
	(is (= "0" (read_square tape_after_execute 2)))
	(is (= "1" (read_square tape_after_execute 3)))
	(is (= "0" (read_square tape_after_execute 4)))
	(is (= "" (read_square tape_after_execute 5)))
)

(deftest should_halt_when_error_happens_in_m_config
	(def m_config_1 (create_m_config "config-1" "" "P0,R" "config-2"))
	(def m_config_2 (create_m_config "config-2" "" "P1,R" "config-3"))
	(def m_configs [m_config_1 m_config_2])
	(def machine (create_machine m_configs))
	(def initial_tape (create_tape))
	
	(def complete_config_after_execute (execute machine initial_tape 5))
	(def tape_after_execute (:tape complete_config_after_execute))
	
	(is (= "0" (read_square tape_after_execute 0)))
	(is (= "1" (read_square tape_after_execute 1)))
	(is (= "" (read_square tape_after_execute 2)))
	(is (= "" (read_square tape_after_execute 3)))
)

(deftest should_write_a_serial_of_characters_with_one_config
	(def begin_config (create_m_config "b" "" "Pe, R, Pe, R, P0, R, R, P0, L, L" "o"))
	(def m_configs [begin_config])
	(def machine (create_machine m_configs))
	(def initial_tape (create_tape))
	
	(def complete_config_after_execute (execute machine initial_tape 5))
	(is (= 2 (:cursor (:machine complete_config_after_execute))))
	
	(def tape_after_execute (:tape complete_config_after_execute))
	(is (= "e" (read_square tape_after_execute 0)))
	(is (= "e" (read_square tape_after_execute 1)))
	(is (= "0" (read_square tape_after_execute 2)))
	(is (= "" (read_square tape_after_execute 3)))
	(is (= "0" (read_square tape_after_execute 4)))
	(is (= "" (read_square tape_after_execute 5)))
	(is (= "" (read_square tape_after_execute 6)))
	
	(print_tape tape_after_execute 10)
)

(deftest should_errase_character_from_tape
	(def begin_config (create_m_config "b" "" "Pe, R, Pe, R, P0, R, R, P0, L, L" "o"))
	(def errase_config (create_m_config "o" "0" "E" "o"))
	(def m_configs [begin_config errase_config])
	(def machine (create_machine m_configs))
	(def initial_tape (create_tape))
	
	(def complete_config_after_execute (execute machine initial_tape 5))
	(is (= 2 (:cursor (:machine complete_config_after_execute))))
	(def tape_after_execute (:tape complete_config_after_execute))	
	(is (= "" (read_square tape_after_execute 2)))
	(is (= "0" (read_square tape_after_execute 4)))
)

(deftest should_support_any_symbol_in_one_config
	(def begin_config (create_m_config "b" "" "Pe, R, Pe, R, P0, R, R, P0, L, L" "o"))
	(def move_config (create_m_config "o" "ANY" "R" "o"))
	(def m_configs [begin_config move_config])
	(def machine (create_machine m_configs))
	(def initial_tape (create_tape))
	
	(def complete_config_after_execute (execute machine initial_tape 5))
	(is (= 6 (:cursor (:machine complete_config_after_execute))))	
)

(deftest should_support_config_doing_nothing
	(def begin_config (create_m_config "b" "" "Pe, R, Pe, R, P0, R, R, P0, L, L" "emp"))
	(def empty_config (create_m_config "emp" "ANY" " " "o"))
	(def errase_config (create_m_config "o" "0" "E" "o"))
	(def m_configs [begin_config empty_config errase_config])
	(def machine (create_machine m_configs))
	(def initial_tape (create_tape))
	
	(def complete_config_after_execute (execute machine initial_tape 5))
	(is (= 2 (:cursor (:machine complete_config_after_execute))))
	(def tape_after_execute (:tape complete_config_after_execute))	
	(is (= "" (read_square tape_after_execute 2)))
	(is (= "0" (read_square tape_after_execute 4)))
)