(ns clorum.core.security
  (:require [noir.util.crypt :as password]))

(defn encrypt
  "Encrypts the passed string."
  [string]
  (str (password/encrypt string)))

(defn encrypt-verify
  "Verifies an encrypted string."
  [raw encrypted]
  (password/compare raw encrypted))
