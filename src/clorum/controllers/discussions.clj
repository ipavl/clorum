(ns clorum.controllers.discussions
  (:require
   [clostache.parser :as clostache]
   [clorum.models.discussions :as discussions-model]))

(defn read-template [template-name]
  (slurp (clojure.java.io/resource
          (str "views/discussions/" template-name ".mustache"))))

(defn render-template [template-file params]
  (clostache/render (read-template template-file) params))

(defn new []
  (render-template "new" {}))

(defn reply [id]
  (render-template "reply" {:id id}))

(defn index []
  (render-template "index" {:discussions (discussions-model/all)}))

(defn show [id]
  (render-template "show" {:discussion (discussions-model/get id) :replies (discussions-model/get-replies id)}))
