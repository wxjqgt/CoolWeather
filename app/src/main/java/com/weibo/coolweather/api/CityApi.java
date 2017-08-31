package com.weibo.coolweather.api;

import com.weibo.coolweather.Constant;
import com.weibo.coolweather.model.db.City;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by weixj on 2017/8/29.
 */

public interface CityApi {
    @GET(Constant.URL.PROVINCE_URL + "/{provinceId}")
    Observable<List<City>> query(@Path("provinceId") int provinceId);
}
