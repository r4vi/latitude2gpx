(defproject latitude2gpx "0.1.0"
  :description "A tool to convert Google Latitude KML files to a GPX tracklog"
  :url "http://github.com/r4vi/latitude2gpx"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/data.zip "0.1.1"]
                 [org.clojure/data.xml "0.0.7"] 
                 [org.clojure/tools.cli "0.2.1"]]
  :main latitude2gpx.core)
