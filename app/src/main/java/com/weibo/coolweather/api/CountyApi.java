package com.weibo.coolweather.api;

import com.weibo.coolweather.Constant;
import com.weibo.coolweather.model.db.County;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by weixj on 2017/8/29.
 */

public interface CountyApi {
    @GET(Constant.URL.PROVINCE_URL + "/{provinceId}/{cityId}")
    Observable<List<County>> query(@Path("provinceId") int provinceId, @Path("cityId") int cityId);
}
