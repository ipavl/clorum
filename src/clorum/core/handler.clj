(ns clorum.core.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [ring.util.response :as resp]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.basic-authentication :refer :all]
            [clorum.models.threads :as threads-model]
            [clorum.controllers.threads :as threads-controller]
            [clorum.controllers.categories :as categories-controller]
            [clorum.controllers.admin.threads :as admin-threads-controller]))

(defn authenticated? [name pass]
  (and (= name "admin")
       (= pass "password")))

(defroutes public-routes
  (GET "/threads" [] (threads-controller/index))
  (GET "/threads/new" [] (threads-controller/new))
  (GET "/threads/:id" [id] (threads-controller/show id))
  (GET "/categories" [] (categories-controller/index))
  (GET "/categories/:category" [category] (categories-controller/show category))
  (POST "/threads/create" [& params]
        (do (threads-model/create params)
          (resp/redirect "/"))) ; ideally, redirect to /threads/id
          ;(resp/redirect (clojure.string/join ["/threads/" (insertID)]))))
  (route/resources "/"))

(defroutes protected-routes
  (GET "/admin/threads" [] (admin-threads-controller/index))
  (GET "/admin/threads/:id/edit" [id] (admin-threads-controller/edit id))
  (GET "/admin/threads/:id/delete" [id]
        (do threads-model/delete id)
          (resp/redirect "/admin"))
  (POST "/admin/threads/:id/save" [& params]
        (do (threads-model/save (:id params) params)
          (resp/redirect "/admin"))))

(defroutes app-routes
  public-routes
  (wrap-basic-authentication protected-routes authenticated?)
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
