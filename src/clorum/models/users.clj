(ns clorum.models.users
  (:refer-clojure :exclude [get])
  (:require [clojure.java.jdbc :as jdbc]
            [java-jdbc.sql :as sql]
            [clorum.core.config :as config]
            [clorum.core.util :as util]))

(defn all
  "Returns all rows in the users table."
  []
  (jdbc/query config/db
              (sql/select * :users)))

(defn get
  "Returns the user with the specified id."
  [id]
  (first (jdbc/query config/db
                     (sql/select * :users (sql/where {:id id})))))

(defn get-by-name
  "Returns the user with the specified name."
  [username]
  (first (jdbc/query config/db
                     (sql/select * :users (sql/where {:username (first username)})))))

(defn create
  "Inserts a new user with the passed parameters."
  [params]
  (if (nil? (get-by-name [(:username params)]))
    (jdbc/insert! config/db :users (merge params {:registered (util/current-time-sql)
                                                :password (util/encrypt (:password params))}))))

(defn save
  "Updates the user with the specified id with the passed parameters."
  [id params]
  (jdbc/update! config/db :users params (sql/where {:id id})))

(defn delete
  "Deletes the user with the specified id."
  [id]
  (jdbc/delete! config/db :users (sql/where {:id id})))
