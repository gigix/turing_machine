(ns turing_machine.core)

(defn empty_string
	"Returns an empty string"
	[input]
	""
)

(defstruct machine :m_configs)

(defstruct tape :squares)

(defn create_machine
	"Build a Turing machine with given m-configs"
	[m_configs]
	(struct machine m_configs)
)

(defn create_tape
	"Build a blank tape on which a Turing machine can operate"
	[]
	(struct tape (iterate empty_string ""))
)

(defn write
	"Write to a square on tape"
	[a_tape location content]
	
)