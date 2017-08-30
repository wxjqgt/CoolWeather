package com.weibo.coolweather.api;

import com.weibo.coolweather.Constant;
import com.weibo.coolweather.model.db.Province;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by weixj on 2017/8/29.
 */

public interface ProvinceApi {
    @GET(Constant.URL.PROVINCE_URL)
    Observable<List<Province>> queryPorvince();
}
