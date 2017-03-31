package com.leo618.apf.manager.net.retrofit;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * function:通用参数添加，提供一个请求所需的参数集合
 *
 * <p></p>
 * Created by lzj on 2017/3/30.
 */
@SuppressWarnings("ALL")
public final class ParamsMap {

    private static final AtomicReference<ParamsMap> INSTANCE = new AtomicReference<>();

    /** 获取管理器实例 */
    public static ParamsMap me() {
        for (; ; ) {
            ParamsMap manager = INSTANCE.get();
            if (manager != null) return manager;
            manager = new ParamsMap();
            if (INSTANCE.compareAndSet(null, manager)) return manager;
        }
    }

    private ParamsMap() {
        if (commParams == null) {
            commParams = new LinkedHashMap<>();
        }
    }

    private volatile Map<String, String> commParams;


    /**
     * 链式调用来设置参数
     *
     * @param key   key
     * @param value value
     * @return 设置好参数的集合
     */
    public ParamsMap with(String key, String value) {
        commParams.put(key, value);
        return me();
    }

    /**
     * 获取初始参数集合
     *
     * @return 初始参数集合
     */
    public Map<String, String> get() {
        Map<String, String> copyMap = new LinkedHashMap<>();
        //添加通用参数
        copyMap.put("showapi_appid", "34670");
        copyMap.put("showapi_sign", "a4d127803b0242fda775b1e9b09a47dc");
        copyMap.put("showapi_res_gzip", "1");
        copyMap.put("showapi_timestamp", String.valueOf(new Date().getTime()));
        copyMap.putAll(commParams);
        commParams.clear();
        return copyMap;
    }
}
