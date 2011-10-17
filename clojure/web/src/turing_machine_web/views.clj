(ns turing_machine_web.views
  	(:use [hiccup core page-helpers])
	(:require [clojure.contrib.string :as s])
)

(defn index-page 
	[machine_description result]
  	(html5
    	[:head
      		[:title "Configure And Run A Turing Machine"]
      		(include-css "/css/style.css")
		]
    	[:body
			[:form {:method "POST" :action "/"}
      			[:textarea {:rows 20 :cols 80 :name "machine_description" :id "machine_description"} machine_description]
				[:br]
				[:input {:type "submit" :value "Run"}]
			]
			[:code [:p (s/join "" result)]]
		]
	)
)
