(ns clorum.controllers.threads
  (:require
   [clostache.parser :as clostache]
   [clorum.models.threads :as threads-models]))

(defn read-template [template-name]
  (slurp (clojure.java.io/resource
          (str "views/threads/" template-name ".mustache"))))

(defn render-template [template-file params]
  (clostache/render (read-template template-file) params))

(defn index[]
  (render-template "index" {:threads (threads-models/all)}))
