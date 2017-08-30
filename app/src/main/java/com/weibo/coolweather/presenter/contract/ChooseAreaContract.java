package com.weibo.coolweather.presenter.contract;

import com.trello.rxlifecycle2.android.FragmentEvent;
import com.weibo.coolweather.model.db.City;
import com.weibo.coolweather.model.db.County;
import com.weibo.coolweather.model.db.Province;
import com.weibo.coolweather.presenter.BasePresenter;
import com.weibo.coolweather.presenter.BaseView;

import java.util.List;

import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by weixj on 2017/8/30.
 */

public class ChooseAreaContract {
    public interface ChooseAreaView extends BaseView {
        void loadProvinceData(List<Province> provinceList);
        void loadCityData(List<City> provinceList);
        void loadCountyData(List<County> provinceList);
        BehaviorSubject<FragmentEvent> lifecycle();
    }

    public interface ChooseAreaPresenter extends BasePresenter {
        void queryProvince();
        void queryCity();
        void queryCounty();
    }
}
