(defproject clorum "0.1.0-SNAPSHOT"
  :description "Basic forum software written in Clojure"
  :url "https://github.com/ipavl/clorum"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/java.jdbc "0.3.6"]
                 [clorum-core "0.1.0-SNAPSHOT"]
                 [compojure "1.3.1"]
                 [java-jdbc/dsl "0.1.1"]
                 [ring/ring-defaults "0.1.3"]
                 [mysql/mysql-connector-java "5.1.34"]
                 [de.ubercode.clostache/clostache "1.4.0"]
                 [ring-basic-authentication "1.0.5"]
                 [clj-time "0.8.0"]
                 [lib-noir "0.9.5"]]
  :plugins [[lein-ring "0.8.13"]
            [codox "0.8.10"]]
  :ring {:handler clorum.core.handler/app}
  :codox {:src-dir-uri "https://github.com/ipavl/clorum/blob/master/"
          :src-linenum-anchor-prefix "L"}
  :profiles
  {:dev {:dependencies [[ring-mock "0.1.5"]]}})
