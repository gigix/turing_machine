(ns turing_machine.fixtures
  (:use [turing_machine.core])
  (:use [turing_machine.tape])
  (:use [turing_machine.m_config])
)

(defn test_machine_complete_config
	[]
	(def _m_config_1 (create_m_config "config-1" "" "P0,R" "0" "R" "config-2"))
	(def _m_config_2 (create_m_config "config-2" "" "P1,R" "1" "R" "config-1"))
	(def _m_configs [_m_config_1 _m_config_2])
	(create_complete_config (create_machine _m_configs) (create_tape))
)
