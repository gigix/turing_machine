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

(defstruct CompleteConfig :machine :tape)

(defn create_complete_config
	[machine tape]
	(struct CompleteConfig machine tape)
)

(defn actual_single_step
	[machine tape current_cursor m_config_to_execute]
	;TODO: execute with :operations instead of :write_content and :move
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

	(create_complete_config
		(create_machine_internal (:m_configs machine) cursor_after_step m_config_after_step)
		tape_after_step)
)

(defn execute_single_step
	"Execute Turing machine one step"
	[machine tape]
	(def current_m_config (:current_m_config machine))
	(def current_cursor (:cursor machine))
	(def current_square_content (read_square tape (:cursor machine)))
	(def m_config_to_execute (find_m_config (:m_configs machine) (:status current_m_config) current_square_content))
	
	(if (= nil m_config_to_execute)
		;TODO: it's not really halt immediately...
		(create_complete_config machine tape)
		(actual_single_step machine tape current_cursor m_config_to_execute)
	)
)

(defn execute
	"Execute Turing machine given steps"
	[machine tape steps]
	(if (= steps 0)
		(create_complete_config machine tape)
		(and 
			(def complete_config (execute_single_step machine tape))
			(execute (:machine complete_config) (:tape complete_config) (- steps 1))
		)
	)
)