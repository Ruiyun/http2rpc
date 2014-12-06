(ns http2rpc.core
  (:import [com.baidu.bjf.remoting.protobuf.annotation Protobuf]
           [com.baidu.jprotobuf.pbrpc ProtobufPRC]
           [com.baidu.jprotobuf.pbrpc.transport RpcClient RpcClientOptions]
           [com.baidu.jprotobuf.pbrpc.client ProtobufRpcProxy]))

(defrecord EchoInfo [^{Protobuf {:description "the message"}, :tag String}
                     message])

(definterface EchoService
  (^{ProtobufPRC {:serviceName "EchoService", :onceTalkTimeout 200}, :tag EchoInfo}
   echo [^EchoInfo request]))

(defn invoke-echo [host port message]
  (let [client   (RpcClient.)
        proxy    (doto (ProtobufRpcProxy. client EchoService) (.setHost host) (.setPort port))
        service  (.proxy proxy)
        request  (EchoInfo. message)
        response (.echo service)]
    (.stop client)
    response))
