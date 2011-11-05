(ns turing_machine.common_functions)

(defstruct FunctionResult :config_name :m_config)

(defn l
	[next_status]
	(def config_name (str "l_" (rand)))
	(struct FunctionResult config_name (str config_name " | ANY | L | " next_status))
)

(defn r
	[next_status]
	(def config_name (str "r_" (rand)))
	(struct FunctionResult config_name (str config_name " | ANY | R | " next_status))
)

(defn f
	[found_status not_found_status symbol_to_find]
	(def first_row (str "f | e | L | f1"))
	(def second_row (str ""))
	; TODO: this function is not finished yet
	(str first_row second_row)
)
