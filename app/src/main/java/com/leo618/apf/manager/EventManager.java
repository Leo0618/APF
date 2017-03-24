package com.leo618.apf.manager;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * function : 事件注册、反注册、发布 帮助类,用于解耦.
 *
 * <p>Created by lzj on 2016/1/21.</p>
 */
@SuppressWarnings("unused")
public final class EventManager {
    private static Bus BUS = new Bus(ThreadEnforcer.MAIN);

    private static Bus instance() {
        return BUS;
    }

    private EventManager() {
    }

    /**
     * 发布事件
     *
     * @param event 事件对象
     */
    public static void post(Object event) {
        instance().post(event);
    }

    /**
     * 注册
     *
     * @param object 当前Activity或Fragment
     */
    public static void register(Object object) {
        instance().register(object);
    }

    /**
     * 反注册
     *
     * @param object 当前Activity或Fragment
     */
    public static void unregister(Object object) {
        instance().unregister(object);
    }
}