(ns clorum.controllers.users
  (:require
   [clorum.core.config :as config]
   [clorum.core.utilities :as util]
   [clorum.models.users :as users-model]))

(defn index []
  (util/render-page "users/index" {:title "User list"
                                   :users (users-model/all)}))

(defn register []
  (util/render-page "users/register" {:title "Register"}))

(defn edit [id]
  (util/render-page "users/edit" {:title "Edit account"
                                  :user (users-model/get id)}))

(defn show [id]
  (def username (:username (users-model/get id)))
  (util/render-page "users/show" {:title (str "Viewing profile " username)
                                  :user (users-model/get id)
                                  :replies (users-model/get-replies username)
                                  :discussions (users-model/get-discussions username)}))
