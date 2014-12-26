(ns clorum.controllers.admin.users
  (:require
   [clostache.parser :as clostache]
   [clorum.core.config :as config]
   [clorum-core.users :as users-model]))

(defn read-template [template-name]
  (slurp (clojure.java.io/resource
          (str "views/admin/users/" template-name ".mustache"))))

(defn render-template [template-file params]
  (clostache/render (read-template template-file) params))

(defn index []
  (render-template "index" {:users (users-model/all config/db)}))

(defn edit [id]
  (render-template "edit" {:user (users-model/get config/db id)}))

(defn show [id]
  (render-template "show" {:user (users-model/get config/db id)}))
