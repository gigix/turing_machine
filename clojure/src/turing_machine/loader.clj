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
	[file_path]
	(def file_content (slurp file_path))
	(def rows (#'clojure.string/split file_content #"\n"))
	(def m_configs (map #(to_m_config %) rows))
	(create_machine m_configs)
)