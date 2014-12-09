(ns http2rpc.handler
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as s]
            [http2rpc.core :refer :all]))

(s/defschema Message {:message String})
(s/defschema Response {:response s/Any})

(defapi app
  {:formats [:json-kw]}
  (swagger-ui)
  (swagger-docs :title "http2rpc")
  ;; (swaggered "invoke" :description "Invoke PBRPC Platform Service (invalid)"
  ;;            (POST* "/Http2RpcService/Invoke" []
  ;;                   :return Response
  ;;                   :body-params [host :- s/Str
  ;;                                 port :- s/Int
  ;;                                 service :- s/Str
  ;;                                 method :- s/Str
  ;;                                 params :- s/Any]
  ;;                   :summary "调用指定的pbrpc服务接口，并获取调用返回值"
  ;;                   (ok {:response (str "invoke, " method ", param, " params " param's type:" (type params))})))
  (swaggered "invoke echo" :description "Invoke the standard Echo Service"
             (POST* "/Http2RpcService/InvokeEcho" []
                    :return Message
                    :body-params [host :- s/Str
                                  {port :- s/Int 8031}
                                  message :- s/Str]
                    :summary "调用新建平台默认的EchoService，用于验证新建平台是否正常工作"
                    (try
                      (ok {:message (.. (invoke-echo host port message) (getMessage))})
                      (catch Exception ex
                        (internal-server-error (str ex)))))))
