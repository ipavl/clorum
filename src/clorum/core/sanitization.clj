(ns clorum.core.sanitization
  (:require [clojure.string :as string]))

(defn author [name]
  "Returns a generic name if the passed name is blank, otherwise returns the passed name."
  (if (string/blank? name)
    "Guest"
    name))

(defn category [cat]
  "Returns a generic category if the passed name is blank, otherwise returns the passed category."
  (if (string/blank? cat)
    "uncategorized"
    cat))
