(ns turing_machine.loader
	(:use [turing_machine.m_config])
	(:use [turing_machine.core])
)

(defn to_m_config
	[row]
	(def cols (#'clojure.string/split row #"\|"))
	(def items (map #(#'clojure.string/trim %) cols))
	
	(def status (last (take 1 items)))
	(def read_symbol (last (take 2 items)))
	(def ops (last (take 3 items)))
	(def next_status (last (take 4 items)))
	
	(create_m_config status read_symbol ops next_status)
)

(defn load_machine
	[machine_description]
	(def rows (#'clojure.string/split machine_description #"\n"))
	(def useful_rows 
		(filter 
			(fn [raw_row] 
				(def row (#'clojure.string/trim raw_row))
				(and (not (empty? row)) (not (= ";" (str (first row)))))
			)
			rows
		)
	)

	(def m_configs (map #(to_m_config %) useful_rows))
	(create_machine m_configs)
)