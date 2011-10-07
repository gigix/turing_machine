(ns turing_machine.m_config_test
  (:use [turing_machine.m_config])
  (:use [clojure.test]))

(deftest should_create_m_config
	(def config (create_m_config "config-1" "0" "" "R" "config-2"))
	(is (= "config-1" (:status config)))
	(is (= "0" (:read_content config)))
	(is (= "" (:write_content config)))
	(is (= "R" (:move config)))
	(is (= "config-2" (:next_status config)))
)

(deftest should_find_m_config
	(def config_1 (create_m_config "config-1" "0" "" "R" "config-2"))
	(def config_2 (create_m_config "config-2" "1" "" "R" "config-1"))
	(def m_configs [config_1 config_2])
	
	(is (= config_1 (find_m_config m_configs "config-1" "0")))
	(is (= config_2 (find_m_config m_configs "config-2" "1")))
	(is (= nil (find_m_config m_configs "config-2" "")))
)

(deftest should_find_m_config_by_status
	(def config_1 (create_m_config "config-1" "0" "" "R" "config-2"))
	(def config_2 (create_m_config "config-2" "1" "" "R" "config-1"))
	(def m_configs [config_1 config_2])
	
	(is (= config_1 (find_m_config_by_status m_configs "config-1")))
	(is (= config_2 (find_m_config_by_status m_configs "config-2")))
	(is (= nil (find_m_config_by_status m_configs "config-3")))
)