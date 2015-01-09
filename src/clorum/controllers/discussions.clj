(ns clorum.controllers.discussions
  (:require
   [clostache.parser :as clostache]
   [clorum.core.config :as config]
   [clorum-core.discussions :as discussions-model]))

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
  (render-template "index" {:discussions (discussions-model/all config/db)}))

(defn recent []
  (render-template "recent" {:discussions (discussions-model/get-recent config/db 10)}))

(defn show [id]
  (render-template "show" {:discussion (discussions-model/get config/db id)
                           :replies (discussions-model/get-replies config/db id)}))
