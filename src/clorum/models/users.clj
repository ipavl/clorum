(ns clorum.models.users
  (:refer-clojure :exclude [get])
  (:require [clojure.java.jdbc :as jdbc]
            [java-jdbc.sql :as sql]
            [clorum.core.config :as config]))

(def timeNow
  (str (java.sql.Timestamp.(System/currentTimeMillis))))

(defn all []
  (jdbc/query config/db
              (sql/select * :users)))

(defn get [username]
  (first (jdbc/query config/db
                     (sql/select * :users (sql/where {:username username})))))

(defn create [params]
  (jdbc/insert! config/db :users (merge params {:registered timeNow})))

(defn save [id params]
  (jdbc/update! config/db :users params (sql/where {:id id})))

(defn delete [id]
  (jdbc/delete! config/db :users (sql/where {:id id})))
