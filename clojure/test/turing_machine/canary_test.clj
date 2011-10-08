(ns turing_machine.canary_test
  (:use [clojure.string])
  (:use [clojure.test])
)

(deftest sample_of_test_case
	(is (= 4 (+ 2 2)))
	(is (= 6 (* 2 3)))
)

(deftest string_split_sample
	(def operations_as_string "P0,R,R")
	(def operations (split operations_as_string, #","))
	(is (= "P0" (first operations)))
	(is (= "R" (first (rest operations))))
	(is (= "R" (last operations)))
)