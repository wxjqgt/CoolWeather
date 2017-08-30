package com.weibo.coolweather.util;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.weibo.coolweather.Constant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by weixj on 2017/8/30.
 */

public final class RetrofitUtil {

    public static <T> T create(Class<T> mClass){
        return RetrofitHelper.retrofit.create(mClass);
    }

    private static class RetrofitHelper{
        private static final Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.URL.WEATHER_API_URL)
                .build();
    }

}
