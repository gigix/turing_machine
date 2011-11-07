(ns turing_machine.common_functions)

(defstruct FunctionResult :config_name :config_table)

(defn l
	[next_status]
	(def config_name (str "l_" (rand)))
	(struct FunctionResult config_name (str config_name " | ANY | L | " next_status "\n"))
)

(defn r
	[next_status]
	(def config_name (str "r_" (rand)))
	(struct FunctionResult config_name (str config_name " | ANY | R | " next_status "\n"))
)

(defn f
	[found_status not_found_status symbol_to_find]
	(def config_post_fix (str "_" (rand)))
	(def config_name (str "f" config_post_fix))
	(struct FunctionResult config_name 
		(str 
			config_name " | e | L | f1" config_post_fix "\n"
			config_name " | ANY | L | f" config_post_fix "\n"
			"f1" config_post_fix " | " symbol_to_find " | | " found_status "\n"
			"f1" config_post_fix " | | R | f2" config_post_fix "\n"
			"f1" config_post_fix " | ANY | R | f1" config_post_fix "\n"
			"f2" config_post_fix " | " symbol_to_find " | | " found_status "\n"
			"f2" config_post_fix " | | R | " not_found_status "\n"
			"f2" config_post_fix " | ANY | R | f1" config_post_fix "\n"
		)
	)
)

(defn e
	[found_status not_found_status symbol_to_find]
	(def config_post_fix (str "_" (rand)))
	(def e_config_name (str "e" config_post_fix))
	(def e1_config_name (str "e1" config_post_fix))
	(def f_configs (f e1_config_name not_found_status symbol_to_find))
	(struct FunctionResult e_config_name 
		(str 
			e_config_name " | ANY | | " (:config_name f_configs) "\n"
			(:config_table f_configs)
			e1_config_name " | ANY | E | " found_status "\n"
		)
	)
)
