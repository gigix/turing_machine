(ns turing_machine.core_test
  (:use [turing_machine.core])
  (:use [clojure.test]))

(deftest should_create_machine_with_configurations
	(def m_config '("config-1" "0" "" "R" "config-2"))
	(def m_configs '(m_config))
	(def my_machine (create_machine m_configs))
)

(deftest should_create_blank_tape
	(def my_tape (create_tape))
)

(deftest should_change_content_of_sqare_on_tape
	(def my_tape (create_tape))
	(write my_tape 0 "x")
	(is (= '("x") (first my_tape)))
)