(defproject funcprogifce "0.1.0-SNAPSHOT"
  :description "Apoio a disciplina de paradigmas de programacao"
  :url "https://github.com/paoloo/FuncProgIFCE"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/data.json "0.2.6"]
                 [compojure "1.4.0"]
                 [ring/ring-defaults "0.1.5"]
                 [ring/ring-json "0.1.2"]
                 [cheshire "4.0.3"]]
  :plugins [[lein-ring "0.9.7"]]
  :ring {:handler funcprogifce.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}})
