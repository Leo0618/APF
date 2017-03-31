package com.leo618.apf.ui.activity;

import android.text.TextUtils;
import android.view.View;

import com.leo618.apf.R;
import com.leo618.apf.base.BaseActivity;
import com.leo618.apf.manager.net.retrofit.ParamsMap;
import com.leo618.apf.manager.net.retrofit.RetrofitManager;
import com.leo618.apf.test.PhoneNumBean;
import com.leo618.apf.test.PhoneNumService;
import com.leo618.utils.UIUtil;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * function:主页
 *
 * <p></p>
 * Created by lzj on 2017/3/24.
 */
public class MainActivity extends BaseActivity {
    @Override
    protected int setContentViewResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void doBusiness() {

    }

    public void getMsg(View view) {
        //test retrofit
        testRetrofit();
    }

    private void testRetrofit() {
        Map<String, String> paramsMap = ParamsMap.me().with("num", "18820285271").get();
        PhoneNumService service = RetrofitManager.createService(PhoneNumService.class);
        RetrofitManager.request(service.getPhoneInfo(paramsMap), new Callback<PhoneNumBean>() {
            @Override
            public void onResponse(Call<PhoneNumBean> call, Response<PhoneNumBean> response) {
                String datas = response.body().getShowapi_res_body().toString();
                UIUtil.showToastShort(datas);
            }

            @Override
            public void onFailure(Call<PhoneNumBean> call, final Throwable t) {
                if (t != null && !TextUtils.isEmpty(t.getMessage())) {
                    UIUtil.showToastShort(t.getMessage());
                } else {
                    UIUtil.showToastShort("onFailure");
                }
            }
        });
    }
}
