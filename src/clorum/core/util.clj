(ns clorum.core.util
  (:require [noir.util.crypt :as password]
            [clj-time.core :as time]
            [clj-time.coerce :as timec]))

(defn current-time-sql
  "Returns the current SQL timestamp."
  []
  (str (timec/to-sql-time (time/now))))

(defn encrypt
  "Encrypts the passed string."
  [string]
  (str (password/encrypt string)))

(defn encrypt-verify
  "Verifies an encrypted string."
  [raw encrypted]
  (password/compare raw encrypted))
