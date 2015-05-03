(ns clorum.controllers.users
  (:require
   [clorum.core.config :as config]
   [clorum.core.utilities :as util]
   [clorum-core.users :as users-model]))

(defn index []
  (util/render-page "users/index" {:users (users-model/all config/db)}))

(defn register []
  (util/render-page "users/register" []))

(defn edit [id]
  (util/render-page "users/edit" {:user (users-model/get config/db id)}))

(defn show [id]
  (util/render-page "users/show" {:user (users-model/get config/db id)
                           :replies (users-model/get-replies config/db (:username (users-model/get config/db id)))
                           :discussions (users-model/get-discussions config/db (:username (users-model/get config/db id)))}))
