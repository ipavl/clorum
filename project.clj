(defproject clorum "0.1.0-SNAPSHOT"
  :description "Basic forum software written in Clojure"
  :url "https://github.com/ipavl/clorum"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/java.jdbc "0.6.1"]
                 [compojure "1.5.1"]
                 [java-jdbc/dsl "0.1.3"]
                 [ring/ring-defaults "0.2.1"]
                 [yogthos/config "0.8"]
                 [org.postgresql/postgresql "9.4.1208"]
                 [mysql/mysql-connector-java "5.1.35"]
                 [de.ubercode.clostache/clostache "1.4.0"]
                 [ring-basic-authentication "1.0.5"]
                 [clj-time "0.12.0"]
                 [lib-noir "0.9.9"]]
  :plugins [[lein-ring "0.8.13"]
            [codox "0.8.10"]]
  :main "clorum.core.handler"
  :ring {:handler clorum.core.handler/app}
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :codox {:src-dir-uri "https://github.com/ipavl/clorum/blob/master/"
          :src-linenum-anchor-prefix "L"}
  :profiles {:prod {:resource-paths ["config/prod"]}
             :dev {:dependencies [[ring-mock "0.1.5"]]
                   :resource-paths ["config/dev"]}})
