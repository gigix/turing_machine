(ns turing_machine.common_functions_test
  	(:use [clojure.test])
	(:use [turing_machine.core])
	(:use [turing_machine.tape])
	(:use [turing_machine.console])
	(:use [turing_machine.common_functions])
)

(deftest should_generate_move_m_configs
	(is (re-matches #"^l_[\.|\d]+ \| ANY \| L \| config_1\n$" (:config_table (l "config_1"))))
	(is (re-matches #"^l_[\.|\d]+$" (:config_name (l "config_1"))))
	(is (re-matches #"^r_[\.|\d]+ \| ANY \| R \| config_2\n$" (:config_table (r "config_2"))))
	(is (re-matches #"^r_[\.|\d]+$" (:config_name (r "config_2"))))

	(def machine_description (:config_table (r "config_1")))
	(def tape (create_tape))
	(def complete_config_after_execution (execute_machine_with_tape machine_description 5 tape))

	(def tape_after_execution (:tape complete_config_after_execution))
	(def machine_after_execution (:machine complete_config_after_execution))
	
	(is (= 1 (:cursor machine_after_execution)))
)

(deftest should_generate_erase_m_configs
	(def tape (write_square (write_square (write_square (create_tape) 0 "e") 1 "e") 3 "x"))
	
	(def machine_description (:config_table (e "config_found" "config_not_found" "x")))
	(def complete_config_after_execution (execute_machine_with_tape_and_cursor machine_description 100 tape 2))

	(def tape_after_execution (:tape complete_config_after_execution))
	(def machine_after_execution (:machine complete_config_after_execution))
	
	(is (= 3 (:cursor machine_after_execution)))
	(is (= "" (read_square tape_after_execution 3)))
)

(deftest should_generate_find_m_configs
	(def tape (write_square (write_square (write_square (create_tape) 0 "e") 1 "e") 3 "x"))
	
	(def machine_description (:config_table (f "config_found" "config_not_found" "x")))	
	(def complete_config_after_execution (execute_machine_with_tape_and_cursor machine_description 100 tape 2))
	
	(def tape_after_execution (:tape complete_config_after_execution))
	(def machine_after_execution (:machine complete_config_after_execution))
	
	(is (= 3 (:cursor machine_after_execution)))
)
