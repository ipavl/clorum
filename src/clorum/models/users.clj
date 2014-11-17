(ns clorum.models.users
  (:refer-clojure :exclude [get])
  (:require [clojure.java.jdbc :as jdbc]
            [java-jdbc.sql :as sql]
            [clorum.core.config :as config]
            [clorum.core.util :as util]))

(defn all []
  (jdbc/query config/db
              (sql/select * :users)))

(defn get [id]
  (first (jdbc/query config/db
                     (sql/select * :users (sql/where {:id id})))))

(defn get-by-name [username]
  (first (jdbc/query config/db
                     (sql/select * :users (sql/where {:username (first username)})))))

(defn create [params]
  (if (nil? (get-by-name [(:username params)]))
    (jdbc/insert! config/db :users (merge params {:registered util/timeNow
                                                :password (util/encrypt (:password params))}))))

(defn save [id params]
  (jdbc/update! config/db :users params (sql/where {:id id})))

(defn delete [id]
  (jdbc/delete! config/db :users (sql/where {:id id})))
