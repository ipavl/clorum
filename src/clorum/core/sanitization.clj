(ns clorum.core.sanitization
  (:require [clojure.string :as string]))

(defmulti parse-int type)
(defmethod parse-int java.lang.Integer [n] n)
(defmethod parse-int java.lang.String [s] (Integer/parseInt s))

(defn author
  "Returns a generic name if the passed name is blank, otherwise returns the passed name."
  [name]
  (if (string/blank? name)
    "Guest"
    name))

(defn category
  "Returns a generic category if the passed name is blank, otherwise returns the passed category."
  [cat]
  (if (string/blank? cat)
    "uncategorized"
    cat))

(defn blank-string
  "Converts the passed string to nil if it is blank, otherwise returns the passed string unchanged."
  [check]
  (if (string/blank? check)
    nil
    check))
