(ns turing_machine.console
	(:use [turing_machine.core])
	(:use [turing_machine.m_config])
	(:use [turing_machine.tape])
	(:use [turing_machine.loader])
	(:gen-class)
)

(defn execute_machine_with_tape_and_cursor
	[machine_description steps initial_tape initial_cursor]
	(def machine (update_cursor (load_machine machine_description) initial_cursor))
	(execute machine initial_tape steps)
)

(defn execute_machine_with_tape
	[machine_description steps initial_tape]
	(execute_machine_with_tape_and_cursor machine_description steps initial_tape 0)
)

(defn execute_machine
	[machine_description steps]
	(def initial_tape (create_tape))
	(def complete_config_after_execute (execute_machine_with_tape machine_description steps initial_tape))
	(truncate_tape (:tape complete_config_after_execute) steps)
)

(defn execute_machine_from_file
	[file_path steps]
	(println (execute_machine (slurp file_path) steps))
)

(defn -main 
	[& args]
  	(if (empty? args)
		(println "usage: java -jar turing_machine.jar machine_file\n  Execute machine described in machine_file")
		(execute_machine_from_file (first args) (new Integer (first (rest args))))
	)
)