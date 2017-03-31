package com.leo618.apf.test;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * function:
 *
 * <p></p>
 * Created by lzj on 2017/3/31.
 */
public interface PhoneNumService {

    @GET("/6-1")
    Call<PhoneNumBean> getPhoneInfo(@QueryMap Map<String, String> params);
}