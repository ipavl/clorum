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

(defn edit [id]
  (render-template "edit" {:user (users-model/get id)}))

(defn show [id]
  (render-template "show" {:user (users-model/get id)
                           :replies (users-model/get-replies (:username (users-model/get id)))
                           :discussions (users-model/get-discussions (:username (users-model/get id)))}))
