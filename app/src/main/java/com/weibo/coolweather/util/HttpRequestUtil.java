package com.weibo.coolweather.util;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by weibo on 2017/9/8.
 */

public final class HttpRequestUtil {
    public static String requestString(String url) {
        try {
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();
            Response response = OkHttpUtil.getOkHttpClient()
                    .newCall(request)
                    .execute();
            String content = response.body().string();
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
