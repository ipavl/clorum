(ns clorum.controllers.admin.threads
  (:require
   [clostache.parser :as clostache]
   [clorum.models.threads :as threads-model]))

(defn read-template [template-name]
  (slurp (clojure.java.io/resource
          (str "views/admin/threads/" template-name ".mustache"))))

(defn render-template [template-file params]
  (clostache/render (read-template template-file) params))

(defn index []
  (render-template "index" {:threads (threads-model/all)}))

(defn edit [id]
  (render-template "edit" {:thread (threads-model/get id)}))
