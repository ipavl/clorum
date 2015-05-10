(ns clorum.core.config)

(def db {:subprotocol "postgresql"
         :subname "//localhost:5432/clorum"
         :user "postgres"
         :password ""
         :zeroDateTimeBehaviour "convertToNull"
         :classname "org.postgresql.Driver"})

(def recent-discussions-count 10)
