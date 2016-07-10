(ns clorum.controllers.admin.users
  (:require
   [clorum.core.config :as config]
   [clorum.core.utilities :as util]
   [clorum.models.users :as users-model]))

(defn index []
  (util/render-page "admin/users/index" {:title "All users"
                                         :users (users-model/all)}))

(defn edit [id]
  (def user (users-model/get id))
  (util/render-page "admin/users/edit" {:title (str "Edit user: " (:username user))
                                        :user user}))

(defn show [id]
  (def user (users-model/get id))
  (util/render-page "admin/users/show" {:title (:username user)
                                        :user user}))
