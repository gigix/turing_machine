(ns turing_machine.common_functions)

(defn l
	[next_status]
	(str "l | ANY | L | " next_status)
)

(defn r
	[next_status]
	(str "r | ANY | R | " next_status)
)