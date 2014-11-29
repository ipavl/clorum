(ns clorum.core.sanitization
  (:require [clojure.string :as string]))

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
