(ns clorum.controllers.admin.discussions
  (:require
   [clostache.parser :as clostache]
   [clorum.models.discussions :as discussions-model]))

(defn read-template [template-name]
  (slurp (clojure.java.io/resource
          (str "views/admin/discussions/" template-name ".mustache"))))

(defn render-template [template-file params]
  (clostache/render (read-template template-file) params))

(defn index []
  (render-template "index" {:discussions (discussions-model/all)}))

(defn edit [id]
  (render-template "edit" {:discussion (discussions-model/get id)}))
