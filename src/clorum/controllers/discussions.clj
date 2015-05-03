(ns clorum.controllers.discussions
  (:require
   [clorum.core.config :as config]
   [clorum.core.utilities :as util]
   [clorum-core.discussions :as discussions-model]))

(defn new []
  (util/render-page "discussions/new" {:title "New discussion"}))

(defn reply [id]
  (util/render-page "discussions/reply" {:title "Reply to discussion" :id id}))

(defn index []
  (util/render-page "discussions/index" {:title "All discussions"
                                         :discussions (discussions-model/all config/db)}))

(defn recent []
  (util/render-page "discussions/recent" {:title "Recent discussions"
                                          :discussions (discussions-model/get-recent config/db 10)}))

(defn show [id]
  (util/render-page "discussions/show" {:discussion (discussions-model/get config/db id)
                                        :replies (discussions-model/get-replies config/db id)}))
