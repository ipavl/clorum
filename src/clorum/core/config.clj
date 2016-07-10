(ns clorum.core.config)

(def db {:subprotocol "postgresql"
         :subname "//localhost:5432/clorum"
         :user "postgres"
         :password ""
         :zeroDateTimeBehaviour "convertToNull"
         :classname "org.postgresql.Driver"})

;; To use MySQL instead, use the following instead of the above and uncomment the dependency in project.clj
;(def db {:subprotocol "mysql"
;         :subname "//localhost:3306/clorum"
;         :user "root"
;         :password ""
;         :zeroDateTimeBehaviour "convertToNull"})

(def recent-discussions-count 10)
