package com.weibo.coolweather.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.weibo.coolweather.Constant;
import com.weibo.coolweather.R;
import com.weibo.coolweather.api.WeatherApi;
import com.weibo.coolweather.util.NetWorkUtil;
import com.weibo.coolweather.util.RetrofitUtil;
import com.weibo.coolweather.util.RxSchedulersUtil;

import butterknife.BindView;

public class WeatherActivity extends BaseActivity {

    @BindView(R.id.title_city)
    TextView titleCity;
    @BindView(R.id.title_update_time)
    TextView titleUpdateTime;
    @BindView(R.id.degree_text)
    TextView degreeText;
    @BindView(R.id.weather_info_text)
    TextView weatherInfoText;
    @BindView(R.id.forecast_layout)
    LinearLayout forecastLayout;
    @BindView(R.id.api_text)
    TextView apiText;
    @BindView(R.id.pm25_text)
    TextView pm25Text;
    @BindView(R.id.comfort_text)
    TextView comfortText;
    @BindView(R.id.car_wash_text)
    TextView carWashText;
    @BindView(R.id.sport_text)
    TextView sportText;
    @BindView(R.id.weather_layout)
    ScrollView weatherLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent != null) {
            String weather_Id = intent.getStringExtra(Constant.WEATHER_ID);
            if (NetWorkUtil.isNetworkConnected(this)) {
                RetrofitUtil.create(WeatherApi.class)
                        .query(weather_Id,Constant.URL.KEY)

                        .compose(bindToLifecycle())
                        .compose(RxSchedulersUtil.ioToMain())
                        .subscribe(weaTher -> Logger.d(weaTher));
            }
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_weather;
    }
}
