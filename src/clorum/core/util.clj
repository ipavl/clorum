(ns clorum.core.util
  (:require [noir.util.crypt :as password]))

(def timeNow
  "Returns the current timestamp."
  (str (java.sql.Timestamp.(System/currentTimeMillis))))

(defn encrypt [string]
  "Encrypts the passed string."
  (str (password/encrypt string)))

(defn encrypt-verify [raw encrypted]
  "Verifies an encrypted string."
  (password/compare raw encrypted))
