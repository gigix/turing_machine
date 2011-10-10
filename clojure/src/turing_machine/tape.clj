(ns turing_machine.tape)

(defstruct Tape :squares)

(defn pad_squares
	[squares]
	(map #(if (empty? %) " " %) squares)
)

(defn print_tape
	[tape length]
	(println)
	(println (interpose " | " (pad_squares (take length (:squares tape)))))
)

(defn create_tape
	"Build a blank tape on which a Turing machine can operate"
	[]
	; TODO: this tape is not infinite...
	(struct Tape (repeat 65535 ""))
)

(defn write_square_in_seq
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

(defn read_square
	"Read content of a square on tape"
	[tape location]
	(last (take (+ location 1) (:squares tape)))
)