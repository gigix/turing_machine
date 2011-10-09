(ns app-test
	(:use [clojure.test]))

(def test-names [
	:turing_machine.canary_test 
	:turing_machine.core_test 
	:turing_machine.tape_test
	:turing_machine.m_config_test
	:turing_machine.loader_test
])

(def test-namespaces
     (map #(symbol (str (name %)))
          test-names))

(defn run
  "Runs all defined tests"
  []
  (println "Loading tests...")
  (apply require :reload-all test-namespaces)
  (apply run-tests test-namespaces))

(defn run-ant []
  (let [rpt report]
    (binding [;; binding to *err* because, in ant, when the test target
              ;; runs after compile-clojure, *out* doesn't print anything
              *out* *err*
              *test-out* *err*
              report (fn report [m]
                         (if (= :summary (:type m))
                           (do (rpt m)
                               (if (or (pos? (:fail m)) 
                                       (pos? (:error m)))
                                 (throw 
                                  (new Exception (str (:fail m) 
                                                      " failures, " 
                                                      (:error m) 
                                                      " errors.")))))
                           (rpt m)))]
      (run))))