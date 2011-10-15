(ns turing-machine-web.test.core
  (:use [turing-machine-web.core])
  (:use [clojure.test]))

(deftest should_run_canary_test
  	(is (= 2 (+ 1 1)))
)
