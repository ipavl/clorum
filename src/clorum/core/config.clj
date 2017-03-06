(ns clorum.core.config
  (:require [config.core :refer [env]]))

(def db {:subprotocol (:clorum-db-subprotocol env)
         :subname (str "//" (:clorum-db-host env) ":" (:clorum-db-port env) "/" (:clorum-db-name env))
         :user (:clorum-db-user env)
         :password (:clorum-db-password env)
         :zeroDateTimeBehaviour "convertToNull"})

(def recent-discussions-count 10)
