(ns clorum.models.discussions
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
              (sql/select * :discussions)))

(defn get [id]
  (first (jdbc/query db
                     (sql/select * :discussions (sql/where {:id id})))))

(defn create [params]
  (jdbc/insert! db :discussions (merge params {:created timeNow :modified timeNow})))

(defn save [id params]
  (jdbc/update! db :discussions params (sql/where {:id id})))

(defn delete [id]
  (jdbc/delete! db :discussions (sql/where {:id id})))
