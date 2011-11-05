(ns turing_machine.m_config)

(defstruct MConfig :status :read_symbol :operations :next_status)

(defn create_m_config
	[status read_symbol operations next_status]
	(struct MConfig status read_symbol operations next_status)
)

(defn find_m_config
	[m_configs status current_square_content]
	(first
		(filter
			(fn [m_config] 
				(and 
					(= status (:status m_config)) 
					(or 
						(= "ANY" (:read_symbol m_config))
						(= current_square_content (:read_symbol m_config))
					)
				)
			)
			m_configs
		)
	)
)

(defn find_m_config_by_status
	[m_configs status]
	(first
		(filter 
			(fn [m_config] (= status (:status m_config))) 
			m_configs
		)
	)
)