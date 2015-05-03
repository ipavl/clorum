(ns clorum.controllers.admin.users
  (:require
   [clorum.core.config :as config]
   [clorum.core.utilities :as util]
   [clorum-core.users :as users-model]))

(defn index []
  (util/render-page "admin/users/index" {:title "All users"
                                         :users (users-model/all config/db)}))

(defn edit [id]
  (def user (users-model/get config/db id))
  (util/render-page "admin/users/edit" {:title (str "Edit user: " (:username user))
                                        :user user}))

(defn show [id]
  (def user (users-model/get config/db id))
  (util/render-page "admin/users/show" {:title (:username user)
                                        :user user}))
