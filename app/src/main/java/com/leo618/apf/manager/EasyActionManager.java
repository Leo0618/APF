package com.leo618.apf.manager;

import android.text.TextUtils;

import com.leo618.apf.base.BaseBean;
import com.leo618.apf.common.Const;
import com.leo618.apf.interf.IActionCallback;
import com.leo618.apf.interf.IRequestCallback;
import com.leo618.apf.manager.net.okhttpwrap.NetManager;
import com.leo618.utils.PackageManagerUtil;
import com.leo618.utils.SPUtil;
import com.leo618.utils.UIUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * function: 主要是使用静态方式处理简单网络操作
 *
 * <p></p>
 * Created by lzj on 2017/3/7.
 */
@SuppressWarnings("ALL")
public final class EasyActionManager {

    private EasyActionManager() {
    }

    /**
     * 检测是否需要显示引导页
     */
    public static void checkIsNeedShowGuide() {
        int lastVersionCode = SPUtil.getInt(Const.KEY_LAST_VERSION_CODE, 0);
        int currentVersionCode = PackageManagerUtil.getVersionCode();
        //上次存储的版本号小于当前的 说明升级了APP或是首次使用，需要引导页
        SPUtil.putBoolean(Const.KEY_NEED_SHOW_GUIDE, lastVersionCode < currentVersionCode);
    }

    /**
     * 退出登录
     */
    public static void logout(IActionCallback callback) {
        requestInner(null, new HashMap<String, String>(), callback);
    }

    // 类内部通用的请求方法
    private static void requestInner(String url, Map<String, String> params, final IActionCallback callback) {
        NetManager.post(url, params, new IRequestCallback<BaseBean>() {
            @Override
            public void onFailure(Exception e) {
                if (e != null && !TextUtils.isEmpty(e.getMessage())) {
                    UIUtil.showToastShort(e.getMessage());
                }
                callback.onCompleted(false);
            }

            @Override
            public void onSuccess(BaseBean bean) {
                if (bean.getStatus() == Const.RESP_OK) {
                    callback.onCompleted(true);
                } else {
                    onFailure(new RuntimeException(bean.getMsg()));
                }
            }
        });
    }
}














