(ns clorum.models.users
  (:refer-clojure :exclude [get])
  (:require [clojure.java.jdbc :as jdbc]
            [java-jdbc.sql :as sql]
            [clorum.core.config :as config]
            [clorum.core.util :as util]))

(defn all []
  "Returns all rows in the users table."
  (jdbc/query config/db
              (sql/select * :users)))

(defn get [id]
  "Returns the user with the specified id."
  (first (jdbc/query config/db
                     (sql/select * :users (sql/where {:id id})))))

(defn get-by-name [username]
  "Returns the user with the specified name."
  (first (jdbc/query config/db
                     (sql/select * :users (sql/where {:username (first username)})))))

(defn create [params]
  "Inserts a new user with the passed parameters."
  (if (nil? (get-by-name [(:username params)]))
    (jdbc/insert! config/db :users (merge params {:registered util/current-time-sql
                                                :password (util/encrypt (:password params))}))))

(defn save [id params]
  "Updates the user with the specified id with the passed parameters."
  (jdbc/update! config/db :users params (sql/where {:id id})))

(defn delete [id]
  "Deletes the user with the specified id."
  (jdbc/delete! config/db :users (sql/where {:id id})))
