(ns clorum.models.discussions
  (:refer-clojure :exclude [get])
  (:require [clojure.java.jdbc :as jdbc]
            [java-jdbc.sql :as sql]
            [clorum.core.config :as config]
            [clorum.core.util :as util]
            [clorum.core.sanitization :as sanitize]
            [clorum.models.users :as users-model]))

(defn all []
  "Returns all rows in the discussions table."
  (jdbc/query config/db
              (sql/select * :discussions)))

(defn get [id]
  "Returns the discussion with the specified id."
  (first (jdbc/query config/db
                     (sql/select * :discussions (sql/where {:id id})))))

(defn get-replies [parent]
  "Returns all rows in the replies table with the specified parent id."
  (jdbc/query config/db
              (sql/select * :replies (sql/where {:parent parent}))))

(defn create [params]
  "Inserts a new discussion with the passed parameters, sanitizing blank author and category fields."
  (def db-user (users-model/get-by-name [(:author params)]))
  (if db-user
    (def verified? (util/encrypt-verify (:password params) (:password db-user))))

  (jdbc/insert! config/db :discussions (merge (apply dissoc params [:password])
                                              {:author (sanitize/author (:author params))
                                               :category (sanitize/category (:category params))
                                               :created util/timeNow
                                               :modified util/timeNow
                                               :verified verified?})))

(defn create-reply [params]
  "Inserts a new reply with the passed parameters, sanitizing blank author fields."
  (def db-user (users-model/get-by-name [(:author params)]))
  (if db-user
    (def verified? (util/encrypt-verify (:password params) (:password db-user))))

  (jdbc/insert! config/db :replies (merge (apply dissoc params [:password])
                                              {:author (sanitize/author (:author params))
                                               :created util/timeNow
                                               :modified util/timeNow
                                               :verified verified?})))

(defn save [id params]
  "Updates the discussion with the specified id with the passed parameters."
  (jdbc/update! config/db :discussions params (sql/where {:id id})))

(defn delete [id]
  "Deletes the discussion with the specified id along with its child replies."
  (jdbc/delete! config/db :discussions (sql/where {:id id}))
  (jdbc/delete! config/db :replies (sql/where {:parent id})))
