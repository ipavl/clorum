(ns clorum.controllers.admin.discussions
  (:require
   [clorum.core.config :as config]
   [clorum.core.utilities :as util]
   [clorum-core.discussions :as discussions-model]))

(defn index []
  (util/render-page "admin/discussions/index" {:discussions (discussions-model/all config/db)}))

(defn edit [id]
  (util/render-page "admin/discussions/edit" {:discussion (discussions-model/get config/db id)}))
