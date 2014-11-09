(ns clorum.controllers.users
  (:require
   [clostache.parser :as clostache]
   [clorum.models.users :as users-model]))

(defn read-template [template-name]
  (slurp (clojure.java.io/resource
          (str "views/users/" template-name ".mustache"))))

(defn render-template [template-file params]
  (clostache/render (read-template template-file) params))

(defn index []
  (render-template "index" {:users (users-model/all)}))

(defn register []
  (render-template "register" []))

(defn show [user]
  (render-template "show" {:user (users-model/get user)}))
