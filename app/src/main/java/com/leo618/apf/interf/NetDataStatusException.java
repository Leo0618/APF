package com.leo618.apf.interf;

/**
 * function:网络请求相应的异常相应消息传递异常
 *
 * <p></p>
 * Created by lzj on 2016/12/13.
 */

@SuppressWarnings("unused")
public class NetDataStatusException extends Exception {

    public String msg;
    public int status = -1;

    public NetDataStatusException(String msg, int status) {
        super(msg);
        this.msg = msg;
        this.status = status;
    }

    public NetDataStatusException(String detailMessage, String msg, int status) {
        super(detailMessage);
        this.msg = msg;
        this.status = status;
    }

    public NetDataStatusException(String detailMessage, Throwable throwable, String msg, int status) {
        super(detailMessage, throwable);
        this.msg = msg;
        this.status = status;
    }

    public NetDataStatusException(Throwable throwable, String msg, int status) {
        super(throwable);
        this.msg = msg;
        this.status = status;
    }

    @Override
    public String toString() {
        return "NetDataStatusException{" +
                "msg='" + msg + '\'' +
                ", status=" + status +
                '}';
    }
}
