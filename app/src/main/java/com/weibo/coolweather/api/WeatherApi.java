package com.weibo.coolweather.api;

import com.weibo.coolweather.model.Weather;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by weixj on 2017/9/1.
 */

public interface WeatherApi {
    //url中有？必须要使用@Query
    @GET("api/weather")
    Observable<Weather> query(@Query("weatherId") String weatherId, @Query("key") String key);
}
