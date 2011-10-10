(ns turing_machine.core
	(:use [turing_machine.tape])
	(:use [turing_machine.m_config])
	(:use [clojure.string])
)

(defstruct Machine :m_configs :cursor :current_m_config)

(defn create_machine
	"Build a Turing machine with given m-configs"
	[m_configs]
	(struct Machine m_configs 0 (first m_configs))
)

(defn update_cursor
	[machine cursor]
	(struct Machine (:m_configs machine) cursor (:current_m_config machine))
)

(defn update_current_m_config
	[machine m_config]
	(struct Machine (:m_configs machine) (:cursor machine) m_config)
)

(defstruct CompleteConfig :machine :tape)

(defn create_complete_config
	[machine tape]
	(struct CompleteConfig machine tape)
)

(defn execute_operation
	[raw_operation machine tape]
	(def operation (#'clojure.string/trim raw_operation))
	(def current_cursor (:cursor machine))
	
	(def machine_with_cursor_determined
		(if (= "R" operation)
			(update_cursor machine (+ current_cursor 1))
			(if (= "L" operation)
				(update_cursor machine (- current_cursor 1))
				machine
			)
		)
	)
	
	(def tape_after_step 
		(if (= "P" (str (first operation)))
			(write_square tape current_cursor (str (last operation)))
			tape
		)
	)
	
	(create_complete_config machine_with_cursor_determined tape_after_step)	
)

(defn execute_operations
	[operations machine tape]
	(if (empty? operations)
		(create_complete_config machine tape)
		(and 
			(def complete_config_after_operation (execute_operation (first operations) machine tape))
			(execute_operations 
				(rest operations) 
				(:machine complete_config_after_operation) 
				(:tape complete_config_after_operation)
			)
		)
	)
)

(defn actual_single_step
	[machine tape m_config_to_execute]
	(def operations (#'clojure.string/split (:operations m_config_to_execute) #","))
	
	(def complete_config_after_operations (execute_operations operations machine tape))
	(def m_config_after_step (find_m_config_by_status (:m_configs machine) (:next_status m_config_to_execute)))

	(create_complete_config
		(update_current_m_config (:machine complete_config_after_operations) m_config_after_step)
		(:tape complete_config_after_operations))
)

(defn execute_single_step
	"Execute Turing machine one step"
	[machine tape]
	(def current_m_config (:current_m_config machine))
	(def current_square_content (read_square tape (:cursor machine)))
	(def m_config_to_execute (find_m_config (:m_configs machine) (:status current_m_config) current_square_content))
	
	(if (= nil m_config_to_execute)
		;TODO: it's not really halt immediately...
		(create_complete_config machine tape)
		(actual_single_step machine tape m_config_to_execute)
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