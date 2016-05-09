(ns funcprogifce.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [ring.middleware.json :as middleware]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
  (GET "/" [] {:body {:text "Hello World"}})
  (route/not-found "Not Found"))

(def app
(-> (handler/api app-routes)
    (middleware/wrap-json-body)
    (middleware/wrap-json-params)
    (middleware/wrap-json-response)))
