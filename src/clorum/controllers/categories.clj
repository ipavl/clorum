(ns clorum.controllers.categories
  (:require
   [clorum.core.config :as config]
   [clorum.core.utilities :as util]
   [clorum.models.categories :as categories-model]))

(defn index []
  (util/render-page "categories/index" {:title "Categories"
                                        :categories (categories-model/all)}))

(defn show [category]
  (util/render-page "categories/show" {:title category
                                       :discussions (categories-model/get category)
                                       :category category}))
