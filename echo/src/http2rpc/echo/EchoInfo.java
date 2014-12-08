package http2rpc.echo;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

/**
 * Created by wenruiyun on 2014/12/7.
 */
public class EchoInfo {
    @Protobuf
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
