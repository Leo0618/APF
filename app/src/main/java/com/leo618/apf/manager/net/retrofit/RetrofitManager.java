package com.leo618.apf.manager.net.retrofit;


import com.leo618.apf.base.BaseBean;
import com.leo618.apf.manager.net.retrofit.fastjsonconvert.FastJsonConverterFactory;
import com.leo618.utils.LogUtil;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * function:Retrofit管理器
 * <p/>
 *
 * Step1:RetrofitManager.createService();
 * <p/>
 * Step2:RetrofitManager.request();
 * <p/>
 *
 * <p/>
 * Created by lzj on 2017/3/30.
 */
@SuppressWarnings("ALL")
public final class RetrofitManager {
    private static final String TAG = "RetrofitManager";

    private static String BASE_URL = "http://route.showapi.com";

    private static volatile List<Object> mCallList = new CopyOnWriteArrayList<>();

    /**
     * 创建请求服务
     */
    public static <T> T createService(Class<T> service) {
        return get().create(service);
    }

    /**
     * 发起请求
     */
    public static <E extends BaseBean> void request(Call<E> call, final Callback<E> callback) {
        if (call == null) return;
        final Call<E> myCall = call;
        mCallList.add(myCall);
        LogUtil.e(TAG, "onRequest : req=" + myCall.request().toString());
        myCall.enqueue(new Callback<E>() {
            @Override
            public void onResponse(Call<E> call, Response<E> response) {
                LogUtil.e(TAG, "onResponse: req=" + call.request().toString() + "\ncode=" + response.code());
                //请求已经取消
                if (!mCallList.contains(myCall)) return;
                //网络访问出错 一般是400 500等错误
                if (!response.isSuccessful()) {
                    onFailure(myCall, new RuntimeException("response error. code=" + response.code()));
                    return;
                }

                //请求成功，正确状态码返回
                E bean = response.body();
                //数据解析错误
                if (bean == null) {
                    onFailure(myCall, new RuntimeException("服务端数据返回格式有误"));
                    return;
                }
                //通用操作处理,日入重登陆等等
                if (bean.getShowapi_res_code() != 0) {
                    onFailure(myCall, new RuntimeException(bean.getShowapi_res_error()));
                    return;
                }
                //回调给UI
                callback.onResponse(myCall, response);
                //移除请求资源
                cancleRequest(myCall);
            }

            @Override
            public void onFailure(Call<E> call, Throwable t) {
                LogUtil.e(TAG, "onFailure=" + (t == null ? "" : t.getMessage()));
                if (!mCallList.contains(myCall)) return;
                callback.onFailure(myCall, t);
                cancleRequest(myCall);
            }
        });
    }

    /**
     * 取消指定请求
     */
    public static <E> void cancleRequest(Call<E> call) {
        boolean result = false;
        if (mCallList.contains(call)) {
            result = mCallList.remove(call);
        }
        LogUtil.e(TAG, "cancleRequest: result=" + result);
    }

    /**
     * 取消所有请求
     */
    public static void releaseAllRequest() {
        mCallList.clear();
        LogUtil.e(TAG, "releaseAllRequest.");
    }

    private RetrofitManager() {
    }

    private static Retrofit get() {
        return new Retrofit.Builder()
                .addConverterFactory(FastJsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }
}
