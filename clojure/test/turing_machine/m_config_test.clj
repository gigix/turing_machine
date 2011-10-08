(ns turing_machine.m_config_test
  (:use [turing_machine.fixtures])
  (:use [turing_machine.m_config])
  (:use [clojure.test]))

(deftest should_create_m_config
	(def config (first (:m_configs (:machine (test_machine_complete_config)))))
	(is (= "config-1" (:status config)))
	(is (= "" (:read_symbol config)))
	(is (= "0" (:write_content config)))
	(is (= "R" (:move config)))
	(is (= "config-2" (:next_status config)))
)

(deftest should_find_m_config
	(def m_configs (:m_configs (:machine (test_machine_complete_config))))
	(def config_1 (first m_configs))
	(def config_2 (last m_configs))
	
	(is (= config_1 (find_m_config m_configs "config-1" "")))
	(is (= config_2 (find_m_config m_configs "config-2" "")))
	(is (= nil (find_m_config m_configs "config-2" "0")))
)

(deftest should_find_m_config_by_status
	(def m_configs (:m_configs (:machine (test_machine_complete_config))))
	(def config_1 (first m_configs))
	(def config_2 (last m_configs))
	
	(is (= config_1 (find_m_config_by_status m_configs "config-1")))
	(is (= config_2 (find_m_config_by_status m_configs "config-2")))
	(is (= nil (find_m_config_by_status m_configs "config-3")))
)