(ns clorum.models.categories
  (:refer-clojure :exclude [get])
  (:require [clojure.java.jdbc :as jdbc]
            [java-jdbc.sql :as sql]))

(def db {:subprotocol "mysql"
         :subname "//localhost:3306/clorum"
         :user "root"
         :pass ""
         :zeroDateTimeBehaviour "convertToNull"})

(def timeNow
  (str (java.sql.Timestamp.(System/currentTimeMillis))))

(defn all []
  (jdbc/query db
              ["SELECT DISTINCT(category) FROM threads"]))

(defn get [category]
  (jdbc/query db
              (sql/select * :threads (sql/where {:category category}))))

;(defn count [category]
;  (get [category])
