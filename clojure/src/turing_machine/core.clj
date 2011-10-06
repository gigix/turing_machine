(ns turing_machine.core)

(defstruct Machine :m_configs)

(defstruct Tape :squares)

(defn create_machine
	"Build a Turing machine with given m-configs"
	[m_configs]
	(struct Machine m_configs)
)

(defn create_tape
	[]
	; TODO: this tape is not infinite...
	(struct Tape (repeat 65535 ""))
)

(defn write_square_in_seq
	"Internal method: write to a square in a sequence of squares"
	[squares head location content]
	(if (= 0 location)
		(into head (cons content (rest squares)))
		(write_square_in_seq (rest squares) (conj head (first squares)) (- location 1) content)
	)
)

(defn write_square
	"Write to a square on tape"
	[tape location content]
	(struct Tape (write_square_in_seq (:squares tape) [] location content))
)

(defn read_square_in_seq
	"Internal method: read content of a square in a sequence of squares"
	[squares location]
	(if (= 0 location) 
		(first squares)
		(read_square_in_seq (rest squares) (- location 1))
	)
)

(defn read_square
	"Read content of a square on tape"
	[tape location]
	(read_square_in_seq (:squares tape) location)
)