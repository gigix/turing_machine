(ns turing_machine.core_test
  (:use [turing_machine.core])
  (:use [turing_machine.tape])
  (:use [turing_machine.m_config])
  (:use [clojure.test]))

(deftest should_create_machine_with_configurations
	(def m_config (create_m_config "config-1" "0" "" "R" "config-2"))
	(def m_configs [m_config])
	(def machine (create_machine m_configs))
)

(deftest should_execute_single_step_according_to_configurations
	(def m_config_1 (create_m_config "config-1" "" "0" "R" "config-2"))
	(def m_config_2 (create_m_config "config-2" "" "1" "R" "config-1"))
	(def m_configs [m_config_1 m_config_2])
	(def machine (create_machine m_configs))
	(def initial_tape (create_tape))
	
	(def pair_after_first_step (execute_single_step machine initial_tape))
	(def machine_after_first_step (first pair_after_first_step))
	(def tape_after_first_step (last pair_after_first_step))
	(print_tape tape_after_first_step 10)
	
	(is (= "0" (read_square tape_after_first_step 0)))
	(is (= "" (read_square tape_after_first_step 1)))
	(is (= "" (read_square tape_after_first_step 2)))
	
	(def pair_after_second_step (execute_single_step machine_after_first_step tape_after_first_step))
	(def machine_after_second_step (first pair_after_second_step))
	(def tape_after_second_step (last pair_after_second_step))
	(print_tape tape_after_second_step 10)

	(is (= "0" (read_square tape_after_second_step 0)))
	(is (= "1" (read_square tape_after_second_step 1)))
	(is (= "" (read_square tape_after_second_step 2)))
)

(deftest should_execute_continuously
	;TODO: write test for it
)