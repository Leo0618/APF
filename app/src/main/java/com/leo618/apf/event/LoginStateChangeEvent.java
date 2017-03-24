package com.leo618.apf.event;


import com.leo618.apf.base.BaseEvent;

/**
 * function: 登录状态改变事件
 *
 * <p></p>
 * Created by lzj on 2016/3/14.
 */
public class LoginStateChangeEvent extends BaseEvent {
    public boolean loginSuccess = false;

    public LoginStateChangeEvent(boolean loginState) {
        this.loginSuccess = loginState;
    }
}
