(ns clorum.models.discussions
  (:refer-clojure :exclude [get])
  (:require [clojure.java.jdbc :as jdbc]
            [java-jdbc.sql :as sql]
            [clorum.core.config :as config]
            [clorum.core.util :as util]
            [clorum.models.users :as users-model]))

(defn all []
  (jdbc/query config/db
              (sql/select * :discussions)))

(defn get [id]
  (first (jdbc/query config/db
                     (sql/select * :discussions (sql/where {:id id})))))

(defn get-replies [parent]
  (jdbc/query config/db
              (sql/select * :replies (sql/where {:parent parent}))))

(defn create [params]
  (def db-user (users-model/get-by-name [(:author params)]))
  (if db-user
    (def verified? (util/encrypt-verify (:password params) (:password db-user))))

  (jdbc/insert! config/db :discussions (merge (apply dissoc params [:password])
                                              {:created util/timeNow :modified util/timeNow :verified verified?})))

(defn create-reply [params]
  (def db-user (users-model/get-by-name [(:author params)]))
  (if db-user
    (def verified? (util/encrypt-verify (:password params) (:password db-user)))

  (jdbc/insert! config/db :replies (merge (apply dissoc params [:password])
                                              {:created util/timeNow :modified util/timeNow :verified verified?})))

(defn save [id params]
  (jdbc/update! config/db :discussions params (sql/where {:id id})))

(defn delete [id]
  (jdbc/delete! config/db :discussions (sql/where {:id id}))
  (jdbc/delete! config/db :replies (sql/where {:parent id})))
