(ns clorum.core.util)

(def timeNow
  (str (java.sql.Timestamp.(System/currentTimeMillis))))
