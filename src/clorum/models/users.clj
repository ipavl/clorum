(ns clorum.models.users
  (:refer-clojure :exclude [get])
  (:require [clojure.java.jdbc :as jdbc]
            [java-jdbc.sql :as sql]
            [clorum.core.config :as config]
            [clorum.core.security :as security]
            [clorum.core.sanitization :as sanitize]))

(defn all
  "Returns all rows in the users table."
  []
  (jdbc/query config/db
              (sql/select * :users)))

(defn get
  "Returns the user with the specified id."
  [id]
  (first (jdbc/query config/db
                     (sql/select * :users (sql/where {:id (sanitize/parse-int id)})))))

(defn get-by-name
  "Returns the user with the specified name."
  [username]
  (first (jdbc/query config/db
                     (sql/select * :users (sql/where {:username (first username)})))))

(defn get-replies
  "Returns all replies made by the user (ignores non-verified entries)."
  [author]
  (jdbc/query config/db
              (sql/select * :replies (sql/where {:author author :verified true}))))

(defn get-discussions
  "Returns all discussions started by the user (ignores non-verified entries)."
  [author]
  (jdbc/query config/db
              (sql/select * :discussions (sql/where {:author author :verified true}))))

(defn create
  "Inserts a new user with the passed parameters."
  [params]
  (if (nil? (get-by-name [(:username params)]))
    (jdbc/insert! config/db :users (merge params {:password (security/encrypt (:password params))}))))

(defn save
  "Updates the user with the specified id with the passed parameters if the given password is correct."
  [id params]
  (if (security/encrypt-verify (:currentpass params) (:password (get id)))
    (jdbc/update! config/db :users (merge (apply dissoc params [:currentpass
                                                         :permissions
                                                         :registered
                                                         :ipaddress
                                                         :username
                                                         :id])
                                                 {:email (sanitize/blank-string (:email params))
                                                  :password (security/encrypt (:password params))}) (sql/where {:id (sanitize/parse-int id)}))))

(defn save-admin
  "Updates the user with the specified id with the passed parameters without verifying the user."
  [id params]
      (jdbc/update! config/db :users (merge
                                      (apply dissoc params [:ipaddress
                                                            :id
                                                            :registered
                                                            :permissions])
                                                 {:email (sanitize/blank-string (:email params))})
                    (sql/where {:id (sanitize/parse-int id)})))

(defn delete
  "Deletes the user with the specified id."
  [id]
  (jdbc/delete! config/db :users (sql/where {:id (sanitize/parse-int id)})))
