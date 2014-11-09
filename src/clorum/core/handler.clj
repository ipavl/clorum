(ns clorum.core.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [ring.util.response :as resp]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.basic-authentication :refer :all]
            [clorum.models.discussions :as discussions-model]
            [clorum.models.users :as users-model]
            [clorum.controllers.discussions :as discussions-controller]
            [clorum.controllers.categories :as categories-controller]
            [clorum.controllers.users :as users-controller]
            [clorum.controllers.admin.discussions :as admin-discussions-controller]
            [clorum.controllers.admin.users :as admin-users-controller]))

(defn authenticated? [name pass]
  (and (= name "admin")
       (= pass "password")))

(defroutes public-routes
  (GET "/" [] (resp/redirect "/categories"))
  (GET "/discussions" [] (discussions-controller/index))
  (GET "/discussions/new" [] (discussions-controller/new))
  (GET "/discussions/:id" [id] (discussions-controller/show id))
  (GET "/discussions/:id/reply" [id] (discussions-controller/reply id))
  (GET "/categories" [] (categories-controller/index))
  (GET "/categories/:category" [category] (categories-controller/show category))
  (GET "/users" [] (users-controller/index))
  (GET "/users/register" [] (users-controller/register))
  (GET "/users/:id" [id] (users-controller/show id))
  (POST "/discussions/create" [& params]
        (do (discussions-model/create params)
          (resp/redirect "/discussions"))) ; ideally, redirect to /discussions/id
          ;(resp/redirect (clojure.string/join ["/discussions/" (insertID)]))))
  (POST "/discussions/:parent/reply/create" [& params]
        (do (discussions-model/create-reply params)
          (resp/redirect "/discussions"))) ; ideally, redirect to /discussions/id
          ;(resp/redirect (clojure.string/join ["/discussions/" (insertID)]))))
  (POST "/users/register/create" [& params]
        (do (users-model/create params)
          (resp/redirect "/")))
  (route/resources "/"))

(defroutes protected-routes
  (GET "/admin/users" [] (admin-users-controller/index))
  (GET "/admin/users/:id" [id] (admin-users-controller/show id))
  (GET "/admin/users/:id/edit" [id] (admin-users-controller/edit id))
  (GET "/admin/discussions" [] (admin-discussions-controller/index))
  (GET "/admin/discussions/:id/edit" [id] (admin-discussions-controller/edit id))
  (GET "/admin/discussions/:id/delete" [id]
        (do discussions-model/delete id)
          (resp/redirect "/admin/discussions"))
  (GET "/admin/users/:id/delete" [id]
        (do users-model/delete id)
          (resp/redirect "/admin/users"))
  (POST "/admin/users/:id/save" [& params]
        (do (users-model/save (:id params) params)
          (resp/redirect "/admin/users")))
  (POST "/admin/discussions/:id/save" [& params]
        (do (discussions-model/save (:id params) params)
          (resp/redirect "/admin/discussions"))))

(defroutes app-routes
  public-routes
  (wrap-basic-authentication protected-routes authenticated?)
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
