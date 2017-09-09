package com.weibo.coolweather.view.activity;

import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.weibo.coolweather.Constant;
import com.weibo.coolweather.R;
import com.weibo.coolweather.model.HeFengWeather;
import com.weibo.coolweather.presenter.BasePresenter;
import com.weibo.coolweather.presenter.WeatherActivityPresenterImp;
import com.weibo.coolweather.presenter.contract.WeatherActivityContract;
import com.weibo.coolweather.util.ImageLoader;
import com.weibo.coolweather.util.SPUtil;

import butterknife.BindView;
import io.reactivex.subjects.BehaviorSubject;

public class WeatherActivity extends BaseActivity implements WeatherActivityContract.WeatherActivityView {

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
    @BindView(R.id.image_backgroud)
    ImageView image_background;
    @BindView(R.id.drawbleLayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.home)
    Button home;

    private WeatherActivityContract.WeatherActivityPresenter weatherActivityPresenter;
    private String weatherId;

    @Override
    protected void loadData() {
        new WeatherActivityPresenterImp(this);
        weatherActivityPresenter.loadBackgroundData();
        weatherId = SPUtil.getString(Constant.LAST_WEATHER_ID);
        if (weatherId.equals("")) {
            weatherId = "CN101010100";
        }
        weatherActivityPresenter.loadWeatherData(weatherId);
    }

    @Override
    protected void lisener() {
        RxView.clicks(home)
                .compose(bindToLifecycle())
                .subscribe(o -> drawerLayout.openDrawer(Gravity.START));

        swipeRefreshLayout.setOnRefreshListener(() -> {
            weatherActivityPresenter.updateWeatherData(weatherId);
        });
    }

    public void closeDrawerLayout() {
        drawerLayout.closeDrawers();
    }

    public void updateWeatherData(String weatherId) {
        this.weatherId = weatherId;
        SPUtil.putString(Constant.LAST_WEATHER_ID, weatherId);
        weatherActivityPresenter.updateWeatherData(weatherId);
    }

    @Override
    public void viewWeatherData(HeFengWeather heFengWeather) {
        weatherLayout.setVisibility(View.GONE);

        titleCity.setText(heFengWeather.getBasic().getCityName());
        titleUpdateTime.setText(heFengWeather.getBasic().getUpdate().getUpdateTime());

        degreeText.setText(heFengWeather.getNow().getTemperature() + "°C");
        weatherInfoText.setText(heFengWeather.getNow().getMore().getInfo());

        comfortText.setText("舒适度: " + heFengWeather.getSuggestion().getComfort().getInfo());
        carWashText.setText("洗车指数: " + heFengWeather.getSuggestion().getCarWash().getInfo());
        sportText.setText("运动建议: " + heFengWeather.getSuggestion().getSport().getInfo());

        String apiInfo = "0";
        String pm25Info = "0";
        if (heFengWeather.getAqi() != null) {
            apiInfo = heFengWeather.getAqi().getCity().getAqi();
            pm25Info = heFengWeather.getAqi().getCity().getAqi();
        }
        apiText.setText(apiInfo);
        pm25Text.setText(pm25Info);

        forecastLayout.removeAllViews();
        for (HeFengWeather.DailyForecast dailyForecast : heFengWeather.getDaily_forecast()) {
            View view = LayoutInflater.from(WeatherActivity.this)
                    .inflate(R.layout.forecast_item, forecastLayout, false);

            TextView dateText = (TextView) view.findViewById(R.id.date_text);
            TextView infoText = (TextView) view.findViewById(R.id.info_text);
            TextView maxText = (TextView) view.findViewById(R.id.max_text);
            TextView minText = (TextView) view.findViewById(R.id.min_text);

            dateText.setText(dailyForecast.getDate());
            infoText.setText(dailyForecast.getMore().getInfo());
            maxText.setText(dailyForecast.getTmperature().getMax());
            minText.setText(dailyForecast.getTmperature().getMin());

            forecastLayout.addView(view);
        }

        weatherLayout.setVisibility(View.VISIBLE);

        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void viewBackgroundData(String imageUrl) {
        ImageLoader.load(this, imageUrl, image_background);
    }

    @Override
    public void networkErrorView() {
        Toast.makeText(this, "当前网络不通！", Toast.LENGTH_LONG).show();
    }

    @Override
    public void setPresenter(BasePresenter basePresenter) {
        weatherActivityPresenter = (WeatherActivityContract.WeatherActivityPresenter) basePresenter;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_weather;
    }

    @Override
    public BehaviorSubject<ActivityEvent> getLifecycle() {
        return lifecycleSubject;
    }

}
