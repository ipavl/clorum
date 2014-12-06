(ns clorum.models.discussions
  (:refer-clojure :exclude [get])
  (:require [clojure.java.jdbc :as jdbc]
            [java-jdbc.sql :as sql]
            [clorum.core.config :as config]
            [clorum.core.util :as util]
            [clorum.core.sanitization :as sanitize]
            [clorum.models.users :as users-model]))

(defn all
  "Returns all rows in the discussions table."
  []
  (jdbc/query config/db
              (sql/select * :discussions)))

(defn get
  "Returns the discussion with the specified id."
  [id]
  (first (jdbc/query config/db
                     (sql/select * :discussions (sql/where {:id id})))))

(defn get-replies
  "Returns all rows in the replies table with the specified parent id."
  [parent]
  (jdbc/query config/db
              (sql/select * :replies (sql/where {:parent parent}))))

(defn create
  "Inserts a new discussion with the passed parameters, sanitizing blank author and category fields."
  [params]
  (def db-user (users-model/get-by-name [(:author params)]))
  (if db-user
    (def verified? (util/encrypt-verify (:password params) (:password db-user)))
    (def verified? false))
  (def insert-time util/current-time-sql)

  (jdbc/insert! config/db :discussions (merge (apply dissoc params [:password])
                                              {:author (sanitize/author (:author params))
                                               :category (sanitize/category (:category params))
                                               :created (insert-time)
                                               :modified (insert-time)
                                               :verified verified?}))
  ;; Rough way of getting the inserted thread's ID. Should probably also check time.
  (last (jdbc/query config/db
                     (sql/select :id :discussions (sql/where {:author (sanitize/author (:author params))
                                                              :category (sanitize/category (:category params))
                                                              :verified verified?})))))

(defn create-reply
  "Inserts a new reply with the passed parameters, sanitizing blank author fields."
  [params]
  (def db-user (users-model/get-by-name [(:author params)]))
  (if db-user
    (def verified? (util/encrypt-verify (:password params) (:password db-user)))
    (def verified? false))

  (jdbc/insert! config/db :replies (merge (apply dissoc params [:password])
                                              {:author (sanitize/author (:author params))
                                               :created (util/current-time-sql)
                                               :modified (util/current-time-sql)
                                               :verified verified?})))

(defn save
  "Updates the discussion with the specified id with the passed parameters."
  [id params]
  (jdbc/update! config/db :discussions params (sql/where {:id id})))

(defn delete
  "Deletes the discussion with the specified id along with its child replies."
  [id]
  (jdbc/delete! config/db :discussions (sql/where {:id id}))
  (jdbc/delete! config/db :replies (sql/where {:parent id})))
