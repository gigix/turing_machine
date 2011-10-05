(ns turing_machine.core)

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
	(repeat 65535 "")
	; TODO: this tape is not infinite...
)

(defn write
	"Write to a square on tape"
	[a_tape location content]
	; TODO: implement me to pass the test
)