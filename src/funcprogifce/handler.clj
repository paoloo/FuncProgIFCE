(ns funcprogifce.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [ring.middleware.json :as middleware]
            [clojure.data.json :as json]
            [clj-http.client :as client]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(def URL "http://api.openweathermap.org/data/2.5/weather?q=Fortaleza,BR&units=metric&appid=85a4e3c55b73909f42c6a23ec35b7147")

(defn tratamento [conteudo]
  (let [_conteudo (into {} conteudo)]
    (let [_corpo (get _conteudo :body)]
      {:body {
            :cidade (get _corpo :name),
            :presao (get-in _corpo [:main :pressure]),
            :velocidadeVento (get-in _corpo [:wind :speed])}})))

(defroutes app-routes
  (GET "/" [] {:body {:text "Hello World"}})
  (GET "/metodo/:palavra" [palavra] {:body {:text palavra, :primeiraLetra (str "" (paolo.demo.Foo/primeiro palavra))}})
  (GET "/variavel" [] {:body {:info paolo.demo.Foo/texto}})
  (GET "/api" [] (tratamento (client/get URL {:as :json})))
  (route/not-found "Not Found"))

(def app
(-> (handler/api app-routes)
    (middleware/wrap-json-body)
    (middleware/wrap-json-params)
    (middleware/wrap-json-response)))
