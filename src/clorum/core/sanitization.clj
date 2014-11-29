(ns clorum.core.sanitization
  (:require [clojure.string :as string]))

(defn author [name]
  (if (string/blank? name)
    "Guest"
    name))

(defn category [cat]
  (if (string/blank? cat)
    "uncategorized"
    cat))
