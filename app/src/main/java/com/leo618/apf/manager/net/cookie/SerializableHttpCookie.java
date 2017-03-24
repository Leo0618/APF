package com.leo618.apf.manager.net.cookie;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.HttpCookie;

/**
 * function: 序列持久化cookie实体类
 *
 * <p>Created by lzj on 2016/3/16.</p>
 */
class SerializableHttpCookie implements Serializable {

    private transient final HttpCookie cookie;
    private transient HttpCookie clientCookie;

    SerializableHttpCookie(HttpCookie cookie) {
        this.cookie = cookie;
    }

    HttpCookie getCookie() {
        HttpCookie bestCookie = cookie;
        if (clientCookie != null) {
            bestCookie = clientCookie;
        }
        return bestCookie;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(cookie.getName());
        out.writeObject(cookie.getValue());
        out.writeObject(cookie.getComment());
        out.writeObject(cookie.getCommentURL());
        out.writeObject(cookie.getDomain());
        out.writeLong(cookie.getMaxAge());
        out.writeObject(cookie.getPath());
        out.writeObject(cookie.getPortlist());
        out.writeInt(cookie.getVersion());
        out.writeBoolean(cookie.getSecure());
        out.writeBoolean(cookie.getDiscard());
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        String name = (String) in.readObject();
        String value = (String) in.readObject();
        clientCookie = new HttpCookie(name, value);
        clientCookie.setComment((String) in.readObject());
        clientCookie.setCommentURL((String) in.readObject());
        clientCookie.setDomain((String) in.readObject());
        clientCookie.setMaxAge(in.readLong());
        clientCookie.setPath((String) in.readObject());
        clientCookie.setPortlist((String) in.readObject());
        clientCookie.setVersion(in.readInt());
        clientCookie.setSecure(in.readBoolean());
        clientCookie.setDiscard(in.readBoolean());
    }
}
