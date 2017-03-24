package com.leo618.apf.interf;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * function:http请求回调接口,支持JsonObject对象
 *
 * <br/>
 * Created by lzj on 2016/3/31.
 */
public abstract class IRequestCallback<T> {

    public final Type mClassType;

    public IRequestCallback() {
        mClassType = getSuperclassTypeParameter(getClass());
    }

    static Type getSuperclassTypeParameter(Class<?> subclass) {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        return ((ParameterizedType) superclass).getActualTypeArguments()[0];
    }

    public void onStart() {
    }

    public abstract void onFailure(Exception e);

    public abstract void onSuccess(T t);

    public void onProgressUpdate(long writedSize, long totalSize, boolean completed) {
    }
}
