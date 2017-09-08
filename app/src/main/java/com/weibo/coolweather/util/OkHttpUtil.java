package com.weibo.coolweather.util;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by weibo on 2017/9/8.
 */

public final class OkHttpUtil {

    public static OkHttpClient getOkHttpClient(){
        return OkHttpClientHolder.INSTACE;
    }

    private static class OkHttpClientHolder{
        private final static OkHttpClient INSTACE = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .build();
    }

}
