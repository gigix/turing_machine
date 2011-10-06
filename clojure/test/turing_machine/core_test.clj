(ns turing_machine.core_test
  (:use [turing_machine.core])
  (:use [clojure.test]))

(deftest should_create_machine_with_configurations
	(def m_config '("config-1" "0" "" "R" "config-2"))
	(def m_configs '(m_config))
	(def machine (create_machine m_configs))
)
