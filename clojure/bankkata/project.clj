(defproject bankkata "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
                 [org.clojure/clojure "1.8.0"]
                 [clojure.java-time "0.2.2"]]

  :profiles {:dev
             { :dependencies [[midje "1.6.0" :exclusions [org.clojure/clojure]]]
              :plugins [[lein-midje "3.2.1"]]}})
