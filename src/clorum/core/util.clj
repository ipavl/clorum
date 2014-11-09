(ns clorum.core.util
  (:require [crypto.password.bcrypt :as password]))

(def timeNow
  (str (java.sql.Timestamp.(System/currentTimeMillis))))

(defn encrypt [string]
  (str (password/encrypt string)))
