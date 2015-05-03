(ns clorum.core.config)

(def db {:subprotocol "mysql"
         :subname "//localhost:3306/clorum"
         :user "root"
         :pass ""
         :zeroDateTimeBehaviour "convertToNull"})

(def recent-discussions-count 10)
