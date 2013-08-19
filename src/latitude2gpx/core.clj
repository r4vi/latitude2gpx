(ns latitude2gpx.core
  (:use [clojure.tools.cli :only (cli)] 
        [clojure.data.xml :only (sexp-as-element emit-str)]
        [clojure.data.zip.xml])
  (:require (clojure [zip :as z] 
                     [xml :as x]
                     ))
  (:gen-class :main true))


(defn date-to-iso [dt]
  (. (java.text.SimpleDateFormat. "yyyy-MM-dd HH:mm:ss.SSSZ") format dt))

(defn clj2gpx [data]
  (sexp-as-element [:gpx {
                      :version "1.0"
                      :creator "latitude2gpx"
                      :xmlns:xsi "http://www.w3.org/2001/XMLSchema-instance"
                      :xmlns "http://www.topografix.com/GPX/1/0"
                      :xsi:schemaLocation "http://www.topografix.com/GPX/1/0 http://www.topografix.com/GPX/1/0/gpx.xsd" }
                    [:time (date-to-iso (java.util.Date.))]
                    [:trk [:trkseg 
                         (map 
                           #(vector :trkpt 
                              (select-keys % [:lat :lon]) 
                                             [:ele 0]
                                             [:time (:time %)]) data)]]]))

(defn kml2clj [x]
  (let [time (-> x first :content first)
        [lon lat alt] (clojure.string/split (-> x second :content first) #" ")]
    {:time time :lat lat :lon lon :alt alt }))

(defn run
  "Converts a Google Latitude Location History KML file to a GPX track log"
  [opts args] 
  (println (str "Options:\n" opts "\n\n")) 
  (println (str "Arguments:\n" args "\n\n"))
  (let [kmlpath (:input opts)
        data (z/xml-zip (x/parse kmlpath))
        points (->  (xml1-> data 
                       :Document
                       :Placemark
                       :gx:Track)
                   first :content rest) 
        breadcrumbs (partition 2 points)
        data (map kml2clj breadcrumbs)
        gpx (clj2gpx data)
        outfile (:output opts )]
    (if outfile
      (spit outfile (emit-str gpx)) 
      gpx) 
    )
  )

(defn -main [& args]
  (let [[opts args banner]
        (cli args
             ["-h" "--help" "Show help" :flag true :default false]
             ["-i" "--input" "REQUIRED: Input latitude KML FILE"]
             ["-o" "--output" "REQUIRED: Output GPX FILE"]
             )]
   (when (:help opts)
     (println banner)
     (System/exit 0))
   (if 
     (and
       (:input opts)
       (:output opts))
     (do
       (println "")
       (run opts args))
     (println banner)) 
     (System/exit 0)))
