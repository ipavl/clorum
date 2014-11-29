(ns clorum.core.util
  (:require [noir.util.crypt :as password]))

(def timeNow
  "Returns the current timestamp."
  (str (java.sql.Timestamp.(System/currentTimeMillis))))

(defn encrypt
  "Encrypts the passed string."
  [string]
  (str (password/encrypt string)))

(defn encrypt-verify
  "Verifies an encrypted string."
  [raw encrypted]
  (password/compare raw encrypted))
