(defproject turing-machine-web "1.0.0-SNAPSHOT"
  	:description "Turing Machine with web UI"
  	:dependencies [
		[org.clojure/clojure "1.2.1"]
		[compojure "0.6.4"]
		[hiccup "0.3.6"]
		[turing-machine "1.0.0-SNAPSHOT"]
	]
	:dev-dependencies [
		[lein-ring "0.4.5"]
	]
	:ring {:handler turing-machine-web.routes/app}
)