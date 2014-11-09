(ns clorum.core.utils)

(def timeNow
  (str (java.sql.Timestamp.(System/currentTimeMillis))))
