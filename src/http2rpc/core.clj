(ns http2rpc.core
  (:import [com.baidu.jprotobuf.pbrpc.transport RpcClient]
           [com.baidu.jprotobuf.pbrpc.client ProtobufRpcProxy]
           [http2rpc.echo EchoInfo EchoService]))

(defn invoke-echo [^String host, ^long port, ^String message]
  (let [client   (RpcClient.)
        proxy    (doto (ProtobufRpcProxy. client EchoService)
                   (.setHost host)
                   (.setPort port))
        service  (.proxy proxy)
        request  (doto (EchoInfo.)
                   (.setMessage message))
        response (.echo service request)]
    (.stop client)
    response))
