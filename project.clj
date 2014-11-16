(defproject clorum "0.1.0-SNAPSHOT"
  :description "Basic forum software written in Clojure"
  :url "https://github.com/ipavl/clorum"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/java.jdbc "0.3.6"]
                 [compojure "1.2.0"]
                 [java-jdbc/dsl "0.1.0"]
                 [ring/ring-defaults "0.1.2"]
                 [mysql/mysql-connector-java "5.1.33"]
                 [de.ubercode.clostache/clostache "1.4.0"]
                 [ring-basic-authentication "1.0.5"]
                 [lib-noir "0.9.4"]]
  :plugins [[lein-ring "0.8.13"]]
  :ring {:handler clorum.core.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})
