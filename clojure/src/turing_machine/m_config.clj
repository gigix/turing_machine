(ns turing_machine.m_config)

(defstruct MConfig :status :read_content :write_content :move :next_status)

(defn create_m_config
	[status read_content write_content move next_status]
	(struct MConfig status read_content write_content move next_status)
)

(def find_m_config)

(defn find_m_config_safe
	[m_configs status current_square_content]
	(def candidate_config (first m_configs))
	(if 
		(and 
			(= status (:status candidate_config)) 
			(= current_square_content (:read_content candidate_config))
		)
		candidate_config
		(find_m_config (rest m_configs) status current_square_content)
	)
)

(defn find_m_config
	[m_configs status current_square_content]
	(if (empty? m_configs)
		nil
		(find_m_config_safe m_configs status current_square_content)
	)
)

(defn find_m_config_by_status
	[m_configs status]
	(first
		(filter 
			(fn [m_config] (= status (:status m_config))) 
			m_configs)
	)
)