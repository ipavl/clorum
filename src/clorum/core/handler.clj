(ns clorum.core.handler
  (:require [clojure.string :as string]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [ring.util.response :as resp]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.basic-authentication :refer :all]
            [clorum.core.config :as config]
            [clorum-core.discussions :as discussions-model]
            [clorum-core.users :as users-model]
            [clorum.controllers.discussions :as discussions-controller]
            [clorum.controllers.categories :as categories-controller]
            [clorum.controllers.users :as users-controller]
            [clorum.controllers.admin.discussions :as admin-discussions-controller]
            [clorum.controllers.admin.users :as admin-users-controller]))

(defn authenticated? [name pass]
  (and (= name "admin")
       (= pass "password")))

(defn- wrap-guard
  "A guard to protect only /admin routes, otherwise something like the 404 page will be
  erroneously protected if its route is below protected-routes."
  [app]
  (let [guard (wrap-basic-authentication app authenticated?)]
    (fn [req]
      (if (re-matches #".*/admin/.*" (:uri req))
        (guard req)
        (app req)))))

(defroutes public-routes
  (GET "/" [] (resp/redirect "/categories"))
  (GET "/recent" [] (discussions-controller/recent))
  (GET "/discussions" [] (discussions-controller/index))
  (GET "/discussions/new" [] (discussions-controller/new))
  (GET "/discussions/:id" [id] (discussions-controller/show id))
  (GET "/discussions/:id/reply" [id] (discussions-controller/reply id))
  (GET "/categories" [] (categories-controller/index))
  (GET "/categories/:category" [category] (categories-controller/show category))
  (GET "/users" [] (users-controller/index))
  (GET "/users/register" [] (users-controller/register))
  (GET "/users/:id" [id] (users-controller/show id))
  (GET "/users/:id/edit" [id] (users-controller/edit id))
  (POST "/discussions/create" [& params]
          (resp/redirect (clojure.string/join ["/discussions/" (:id (discussions-model/create config/db params))])))
  (POST "/discussions/:parent/reply/create" [& params]
        (do (discussions-model/create-reply config/db params)
          (resp/redirect (string/join ["/discussions/" (:parent params)]))))
  (POST "/users/register/create" [& params]
        (do (if (users-model/create config/db params)
              {:status 200
                :body "Registration successful."}
              {:status 200
                :body "Registration unsuccessful. The username you requested may already be taken."})))
  (POST "/users/:id/save" [& params]
        (do (if (users-model/save config/db (:id params) params)
              {:status 200
                :body "Update successful."}
              {:status 200
                :body "Update unsuccessful. The current password you submitted was probably incorrect."})))
  (route/resources "/"))

(defroutes protected-routes
  (GET "/admin/users" [] (admin-users-controller/index))
  (GET "/admin/users/:id" [id] (admin-users-controller/show id))
  (GET "/admin/users/:id/edit" [id] (admin-users-controller/edit id))
  (GET "/admin/discussions" [] (admin-discussions-controller/index))
  (GET "/admin/discussions/:id/edit" [id] (admin-discussions-controller/edit id))
  (GET "/admin/discussions/:id/delete" [id]
        (do discussions-model/delete config/db id)
          (resp/redirect "/admin/discussions"))
  (GET "/admin/users/:id/delete" [id]
        (do users-model/delete config/db id)
          (resp/redirect "/admin/users"))
  (POST "/admin/users/:id/save" [& params]
        (do (users-model/save-admin config/db (:id params) params)
          (resp/redirect "/admin/users")))
  (POST "/admin/discussions/:id/save" [& params]
        (do (discussions-model/save config/db (:id params) params)
          (resp/redirect "/admin/discussions"))))

(defroutes app-routes
  public-routes
  (wrap-guard protected-routes)
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
