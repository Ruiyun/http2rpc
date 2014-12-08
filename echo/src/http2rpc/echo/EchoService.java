package http2rpc.echo;

import com.baidu.jprotobuf.pbrpc.ProtobufRPC;

/**
 * Created by wenruiyun on 2014/12/7.
 */
public interface EchoService {
    @ProtobufRPC(serviceName = "echoService", onceTalkTimeout = 200)
    EchoInfo echo(EchoInfo info);
}