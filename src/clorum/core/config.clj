(ns clorum.core.config)

(def db {:subprotocol "mysql"
         :subname "//localhost:3306/clorum"
         :user "root"
         :password ""
         :zeroDateTimeBehaviour "convertToNull"})

(def recent-discussions-count 10)
