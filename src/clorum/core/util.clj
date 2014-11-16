(ns clorum.core.util
  (:require [noir.util.crypt :as password]))

(def timeNow
  (str (java.sql.Timestamp.(System/currentTimeMillis))))

(defn encrypt [string]
  (str (password/encrypt string)))

(defn encrypt-verify [raw encrypted]
  (password/compare raw encrypted))
