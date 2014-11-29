(ns clorum.models.categories
  (:refer-clojure :exclude [get])
  (:require [clojure.java.jdbc :as jdbc]
            [java-jdbc.sql :as sql]
            [clorum.core.config :as config]))

(defn all []
  "Returns all unique categories with the number of discussions ordered from the most to least."
  (jdbc/query config/db
              ["SELECT category, COUNT(1) AS discussions FROM discussions GROUP BY category ORDER BY discussions DESC"]))

(defn get [category]
  "Returns all rows matching the specified category."
  (jdbc/query config/db
              (sql/select * :discussions (sql/where {:category category}))))
