(ns clorum.controllers.threads
  (:require
   [clostache.parser :as clostache]
   [clorum.models.threads :as threads-model]))

(defn read-template [template-name]
  (slurp (clojure.java.io/resource
          (str "views/threads/" template-name ".mustache"))))

(defn render-template [template-file params]
  (clostache/render (read-template template-file) params))

(defn new []
  (render-template "new" {}))

(defn index []
  (render-template "index" {:threads (threads-model/all)}))

(defn show [id]
  (render-template "show" {:thread (threads-model/get id)}))
