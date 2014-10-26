(ns clorum.core.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [clorum.controllers.threads :as threads-controller]))

(defroutes app-routes
  (GET "/" [] (threads-controller/index))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
