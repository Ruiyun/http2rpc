(defproject http2rpc "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [metosin/compojure-api "0.16.5" :exclusions [org.clojure/java.classpath
                                                              slingshot
                                                              commons-codec]]
                 [metosin/ring-http-response "0.5.2"]
                 [metosin/ring-swagger-ui "2.0.17"]
                 [clj-time "0.6.0"]
                 [com.fasterxml.jackson.core/jackson-core "2.3.2"]
                 [com.baidu/jprotobuf-rpc-socket "2.6"]]
  :global-vars {*warn-on-reflection* true}
  :ring {:handler http2rpc.handler/app}
  :profiles {:uberjar {:resource-paths ["swagger-ui"]}
             :dev {:dependencies [[javax.servlet/servlet-api "2.5"]]
                   :plugins [[lein-ring "0.8.13" :exclusions [org.clojure/clojure]]]}})
