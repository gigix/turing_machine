(ns turing_machine.m_config)

(defstruct MConfig :status :read_content :write_content :move :next_status)

(defn create_m_config
	[status read_content write_content move next_status]
	(struct MConfig status read_content write_content move next_status)
)

(defn find_m_config
	[m_configs status current_square_content]
	(first
		(filter
			(fn [m_config] 
				(and (= status (:status m_config)) (= current_square_content (:read_content m_config)))
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