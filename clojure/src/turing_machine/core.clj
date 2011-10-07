(ns turing_machine.core
	(:use [turing_machine.tape])
	(:use [turing_machine.m_config])
)

(defstruct Machine :m_configs :cursor :current_m_config)

(defn create_machine_internal
	[m_configs cursor current_m_config]
	(struct Machine m_configs cursor current_m_config)
)

(defn create_machine
	"Build a Turing machine with given m-configs"
	[m_configs]
	(create_machine_internal m_configs 0 (first m_configs))
)

(defn execute_single_step
	"Execute Turing machine one step"
	[machine tape]
	(def current_m_config (:current_m_config machine))
	(def current_cursor (:cursor machine))
	(def current_square_content (read_square tape (:cursor machine)))
	(def m_config_to_execute (find_m_config (:m_configs machine) (:status current_m_config) current_square_content))
	
	(def tape_after_step (write_square tape current_cursor (:write_content m_config_to_execute)))

	(def cursor_after_step
		(if (= "R" (:move m_config_to_execute))
			(+ current_cursor 1)
			(if (= "L" (:move m_config_to_execute))
				(- current_cursor 1)
				current_cursor
			)
		)
	)
	
	(def m_config_after_step (find_m_config_by_status (:m_configs machine) (:next_status m_config_to_execute)))

	[(create_machine_internal (:m_configs machine) cursor_after_step m_config_after_step) tape_after_step]
)