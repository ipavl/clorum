(ns clorum.controllers.categories
  (:require
   [clostache.parser :as clostache]
   [clorum.models.categories :as categories-model]))

(defn read-template [template-name]
  (slurp (clojure.java.io/resource
          (str "views/categories/" template-name ".mustache"))))

(defn render-template [template-file params]
  (clostache/render (read-template template-file) params))

(defn index []
  (render-template "index" {:categories (categories-model/all)}))

(defn show [category]
  (render-template "show" {:discussions (categories-model/get category) :category category}))
