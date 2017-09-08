package com.weibo.coolweather.util;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by weibo on 2017/9/8.
 */

public final class HttpRequestUtil {

    public static String requestString(String url) {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try {
            Response response = OkHttpUtil.getOkHttpClient().newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
