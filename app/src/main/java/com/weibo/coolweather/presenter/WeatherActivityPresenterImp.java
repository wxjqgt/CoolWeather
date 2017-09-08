package com.weibo.coolweather.presenter;

import com.trello.rxlifecycle2.android.RxLifecycleAndroid;
import com.weibo.coolweather.Constant;
import com.weibo.coolweather.model.HeFengWeather;
import com.weibo.coolweather.presenter.contract.WeatherActivityContract;
import com.weibo.coolweather.util.HttpRequestUtil;
import com.weibo.coolweather.util.JSONParseUtil;
import com.weibo.coolweather.util.RxSchedulersUtil;
import com.weibo.coolweather.util.SPUtil;

import io.reactivex.Observable;

/**
 * Created by weibo on 2017/9/8.
 */

public class WeatherActivityPresenterImp implements WeatherActivityContract.WeatherActivityPresenter {

    private WeatherActivityContract.WeatherActivityView weatherActivityView;

    public WeatherActivityPresenterImp(WeatherActivityContract.WeatherActivityView weatherActivityView) {
        this.weatherActivityView = weatherActivityView;
        this.weatherActivityView.setPresenter(this);
    }

    @Override
    public void initWeatherData(String weatherId) {
        Observable.just(weatherId)
                .map(s -> {
                    String content = SPUtil.getString(weatherId);
                    if (content.length() <= 0 || content.equals("")) {
                        content = request(weatherId);
                    }
                    HeFengWeather heFengWeather = JSONParseUtil.parseToWeatherModel(content);
                    return heFengWeather;
                })
                .compose(RxSchedulersUtil.ioToMain())
                .compose(RxLifecycleAndroid.bindActivity(weatherActivityView.getLifecycle()))
                .subscribe(heFengWeather -> weatherActivityView.viewWeatherData(heFengWeather));
    }

    @Override
    public void updateWeatherData(String weatherId) {
        Observable.just(weatherId)
                .flatMap(s -> {
                    HeFengWeather heFengWeather = JSONParseUtil.parseToWeatherModel(s);
                    return Observable.just(heFengWeather);
                })
                .compose(RxSchedulersUtil.ioToMain())
                .compose(RxLifecycleAndroid.bindActivity(weatherActivityView.getLifecycle()))
                .subscribe(heFengWeather -> weatherActivityView.viewWeatherData(heFengWeather));
    }

    private String request(String weatherId) {
        String url = Constant.URL.WEATHER_API_URL +
                "api/weather?cityid=" + weatherId + "&key=" + Constant.URL.KEY;
        String content = HttpRequestUtil.requestString(url);
        SPUtil.putString(weatherId, content);
        return content;
    }
}
