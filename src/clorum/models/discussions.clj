(ns clorum.models.discussions
  (:refer-clojure :exclude [get])
  (:require [clojure.java.jdbc :as jdbc]
            [java-jdbc.sql :as sql]
            [clorum.core.config :as config]
            [clorum.core.util :as util]))

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
  (jdbc/insert! config/db :discussions (merge params {:created util/timeNow :modified util/timeNow})))

(defn create-reply [params]
  (jdbc/insert! config/db :replies (merge params {:created util/timeNow :modified util/timeNow})))

(defn save [id params]
  (jdbc/update! config/db :discussions params (sql/where {:id id})))

(defn delete [id]
  (jdbc/delete! config/db :discussions (sql/where {:id id}))
  (jdbc/delete! config/db :replies (sql/where {:parent id})))
