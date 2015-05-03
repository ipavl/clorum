(ns clorum.controllers.admin.users
  (:require
   [clorum.core.config :as config]
   [clorum.core.utilities :as util]
   [clorum-core.users :as users-model]))

(defn index []
  (util/render-page "admin/users/index" {:users (users-model/all config/db)}))

(defn edit [id]
  (util/render-page "admin/users/edit" {:user (users-model/get config/db id)}))

(defn show [id]
  (util/render-page "admin/users/show" {:user (users-model/get config/db id)}))
