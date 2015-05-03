(ns clorum.core.utilities
  (:require
   [clojure.java.io :as io]
   [clostache.parser :refer [render-resource]]))

(defn render-page
  "Pass in the path to a template name relative to the `views` folder
  (as a string without the .mustache extension) and the data for the
  template (a map). This method includes the partials for the header
  and footer."
  [template data]
  (render-resource
   (str "views/" template ".mustache")
   data
   (reduce (fn [accum part]
             (assoc accum part (slurp (io/resource (str "views/_partials/"
                                                        (name part)
                                                        ".mustache")))))
           {}
           [:header :footer])))

(defn render-page-partials
  "Pass in the path to a template name relative to the `views` folder
  (as a string without the .mustache extension), the data for the template (a map),
  and a list of partials (keywords) corresponding to like-named template filenames.
  Partials should be placed in the `views/_partials` directory."
  [template data partials]
  (render-resource
   (str "views/" template ".mustache")
   data
   (reduce (fn [accum part]
             (assoc accum part (slurp (io/resource (str "views/_partials/"
                                                        (name part)
                                                        ".mustache")))))
           {}
           partials)))
