(ns turing-machine-web.routes
  	(:use compojure.core
		[hiccup.middleware :only (wrap-base-url)]
		turing_machine_web.views
		turing_machine.console
	)
  	(:require [compojure.route :as route]
		[compojure.handler :as handler])
)

(defroutes main-routes
  	(GET "/" [] (index-page "" ""))
	(POST "/" 
		[machine_description]
		(index-page machine_description (execute_machine machine_description 100))
	)

  	(route/resources "/")
  	(route/not-found "Page not found")
)

(def app
  (-> (handler/site main-routes) (wrap-base-url))
)
