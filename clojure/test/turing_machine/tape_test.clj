(ns turing_machine.tape_test
  (:use [turing_machine.tape])
  (:use [clojure.test]))

(deftest should_create_blank_tape
	(def tape (create_tape))
	(is (= "" (read_square tape 0)))
	(is (= "" (read_square tape 1)))
)

(deftest should_change_content_of_sqare_on_tape
	(def tape (create_tape))

	(def tape_after_first_write (write_square tape 0 "x"))
	(is (= "x" (read_square tape_after_first_write 0)))
	(is (= "" (read_square tape_after_first_write 1)))

	(def tape_after_second_write (write_square tape_after_first_write 1 "y"))
	(is (= "x" (read_square tape_after_second_write 0)))
	(is (= "y" (read_square tape_after_second_write 1)))
	(is (= "" (read_square tape_after_second_write 2)))

	(def tape_after_third_write (write_square tape_after_second_write 2 "z"))
	(is (= "x" (read_square tape_after_third_write 0)))
	(is (= "y" (read_square tape_after_third_write 1)))
	(is (= "z" (read_square tape_after_third_write 2)))
	(is (= "" (read_square tape_after_third_write 3)))
)
