(ns turing_machine.canary_test
  (:use [turing_machine.core])
  (:use [clojure.test]))

(deftest sample
	(is (= 4 (+ 2 2)))
)

(deftest another_sample
	(is (= 6 (* 2 3)))
)
