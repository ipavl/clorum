(ns clorum.controllers.admin.discussions
  (:require
   [clorum.core.config :as config]
   [clorum.core.utilities :as util]
   [clorum.models.discussions :as discussions-model]))

(defn index []
  (util/render-page "admin/discussions/index" {:title "All discussions"
                                               :discussions (discussions-model/all)}))

(defn edit [id]
  (util/render-page "admin/discussions/edit" {:title "Edit discussion"
                                              :discussion (discussions-model/get id)}))
