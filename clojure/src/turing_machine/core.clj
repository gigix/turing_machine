(ns turing_machine.core
	(:use [turing_machine.tape])
)

(defstruct Machine :m_configs)

(defn create_machine
	"Build a Turing machine with given m-configs"
	[m_configs]
	(struct Machine m_configs)
)
