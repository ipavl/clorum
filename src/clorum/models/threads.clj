(ns clorum.models.threads
  (:refer-clojure :exclude [get])
  (:require [clojure.java.jdbc :as jdbc]
            [java-jdbc.sql :as sql]))

(def db {:subprotocol "mysql"
         :subname "//localhost:3306/clorum"
         :user "root"
         :pass ""
         :zeroDateTimeBehaviour "convertToNull"})

;; Returns a string of the current time in milliseconds
(def timeNow
  (str (java.sql.Timestamp.(System/currentTimeMillis))))

;; Returns all threads from the database
(defn all[]
  (jdbc/query db
              (sql/select * :threads)))
