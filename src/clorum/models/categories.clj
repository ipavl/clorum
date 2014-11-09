(ns clorum.models.categories
  (:refer-clojure :exclude [get])
  (:require [clojure.java.jdbc :as jdbc]
            [java-jdbc.sql :as sql]
            [clorum.core.config :as config]))

(def timeNow
  (str (java.sql.Timestamp.(System/currentTimeMillis))))

(defn all []
  (jdbc/query config/db
              ["SELECT category, COUNT(1) AS discussions FROM discussions GROUP BY category ORDER BY discussions DESC"]))

(defn get [category]
  (jdbc/query config/db
              (sql/select * :discussions (sql/where {:category category}))))
