(ns turing_machine.loader_test
  	(:use [turing_machine.loader])
  	(:use [turing_machine.tape])
  	(:use [turing_machine.core])
	(:use [clojure.test])
)

(deftest should_load_machine_from_file
	(def machine (load_machine (slurp "machines/one_third.machine")))
	(def initial_tape (create_tape))
	
	(def complete_config_after_execute (execute machine initial_tape 5))
	(def tape_after_execute (:tape complete_config_after_execute))
	
	(is (= "0" (read_square tape_after_execute 0)))
	(is (= "1" (read_square tape_after_execute 1)))
	(is (= "0" (read_square tape_after_execute 2)))
	(is (= "1" (read_square tape_after_execute 3)))
	(is (= "0" (read_square tape_after_execute 4)))
	(is (= "" (read_square tape_after_execute 5)))
)
