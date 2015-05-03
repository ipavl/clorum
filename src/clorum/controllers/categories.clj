(ns clorum.controllers.categories
  (:require
   [clorum.core.config :as config]
   [clorum.core.utilities :as util]
   [clorum-core.categories :as categories-model]))

(defn index []
  (util/render-page "categories/index" {:categories (categories-model/all config/db)}))

(defn show [category]
  (util/render-page "categories/show" {:discussions (categories-model/get config/db category)
                            :category category}))
