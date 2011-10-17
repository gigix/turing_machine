(ns turing_machine.console
	(:use [turing_machine.core])
	(:use [turing_machine.m_config])
	(:use [turing_machine.tape])
	(:use [turing_machine.loader])
	(:gen-class)
)

(defn execute_machine
	[machine_description steps]
	(def machine (load_machine machine_description))
	(def initial_tape (create_tape))

	(def complete_config_after_execute (execute machine initial_tape steps))
	(truncate_tape (:tape complete_config_after_execute) steps)
)

(defn execute_machine_from_file
	[file_path steps]
	(println (execute_machine (slurp file_path)))
)

(defn -main 
	[& args]
  	(if (empty? args)
		(println "usage: java -jar turing_machine.jar machine_file\n  Execute machine described in machine_file")
		(execute_machine (first args) (new Integer (first (rest args))))
	)
)