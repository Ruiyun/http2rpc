(ns http2rpc.handler
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as s]))

(s/defschema Message {:response String})

(defapi app
  {:formats [:json-kw]}
  (swagger-ui)
  (swagger-docs
    :title "http2rpc")
  (swaggered "invoke"
    :description "Invoke PBRPC Platform Service"
    (POST* "/Http2RpcService/Invoke" []
      :return Message
      :body-params [host :- s/Str
                    port :- s/Int
                    service :- s/Str
                    method :- s/Str
                    params :- s/Any]
      :summary "调用指定的pbrpc服务接口，并获取调用返回值"
      (ok {:response (str "invoke, " method ", param, " (class params))}))))
