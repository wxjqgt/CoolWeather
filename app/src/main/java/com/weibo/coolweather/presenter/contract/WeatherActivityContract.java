package com.weibo.coolweather.presenter.contract;

import com.trello.rxlifecycle2.android.ActivityEvent;
import com.weibo.coolweather.model.HeFengWeather;
import com.weibo.coolweather.presenter.BasePresenter;
import com.weibo.coolweather.presenter.BaseView;

import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by weibo on 2017/9/8.
 */

public class WeatherActivityContract {
    public interface WeatherActivityView extends BaseView {
        //显示数据到View上
        void viewWeatherData(HeFengWeather heFengWeather);
        //网络错误提示
        void networkErrorView();
        //获取生命周期管理
        BehaviorSubject<ActivityEvent> getLifecycle();
    }

    public interface WeatherActivityPresenter extends BasePresenter {
        void initWeatherData(String weatherId);
        void updateWeatherData(String weatherId);
    }
}
