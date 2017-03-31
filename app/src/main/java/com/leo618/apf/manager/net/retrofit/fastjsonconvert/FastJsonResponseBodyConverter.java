package com.leo618.apf.manager.net.retrofit.fastjsonconvert;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.Okio;
import retrofit2.Converter;

/**
 * function:
 *
 * <p></p>
 * Created by lzj on 2017/3/30.
 */
class FastJsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Type type;

    FastJsonResponseBodyConverter(Type type) {
        this.type = type;
    }

    /*
    * 转换方法
    */
    @Override
    public T convert(ResponseBody value) throws IOException {
        BufferedSource bufferedSource = Okio.buffer(value.source());
        String tempStr = bufferedSource.readUtf8();
        bufferedSource.close();
        return JSON.parseObject(tempStr, type);

    }
}
