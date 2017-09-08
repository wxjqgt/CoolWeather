package com.weibo.coolweather.presenter;

import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;
import com.weibo.coolweather.api.CityApi;
import com.weibo.coolweather.api.CountyApi;
import com.weibo.coolweather.api.ProvinceApi;
import com.weibo.coolweather.app.CoolWeatherApp;
import com.weibo.coolweather.model.db.City;
import com.weibo.coolweather.model.db.County;
import com.weibo.coolweather.model.db.Province;
import com.weibo.coolweather.presenter.contract.ChooseAreaContract;
import com.weibo.coolweather.util.ModelUtil;
import com.weibo.coolweather.util.NetWorkUtil;
import com.weibo.coolweather.util.RetrofitUtil;
import com.weibo.coolweather.util.RxSchedulersUtil;

import org.litepal.crud.DataSupport;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by weixj on 2017/8/30.
 */

public class ChooseAreaPresenterImp implements ChooseAreaContract.ChooseAreaPresenter {

    private ChooseAreaContract.ChooseAreaView chooseAreaView;
    private BehaviorSubject<FragmentEvent> subject;

    public ChooseAreaPresenterImp(BaseView baseView) {
        this.chooseAreaView = (ChooseAreaContract.ChooseAreaView) baseView;
        baseView.setPresenter(this);
        subject = chooseAreaView.getLifecycle();
    }

    @Override
    public void queryProvince() {
        Observable.just(DataSupport.findAll(Province.class))
                .flatMap(provinceList -> {
                    if (provinceList == null || provinceList.size() == 0) {
                        if (NetWorkUtil.isNetworkConnected(CoolWeatherApp.getContext())) {
                            return RetrofitUtil.create(ProvinceApi.class)
                                    .query()
                                    .map(provinceList12 ->
                                            ModelUtil.addProvinceCodeAndNameToProvince(provinceList12)
                                    )
                                    .flatMap(provinceList1 -> {
                                        DataSupport.saveAll(provinceList1);
                                        return Observable.fromArray(provinceList1);
                                    });
                        }
                        chooseAreaView.networkErrorView();
                    }
                    return Observable.fromArray(provinceList);
                })
                .compose(RxSchedulersUtil.ioToMain())
                .compose(RxLifecycleAndroid.bindFragment(subject))
                .subscribe(provinces -> {
                            chooseAreaView.loadProvinceData(provinces);
                        }, throwable ->
                                Logger.e(throwable, "cityError")
                );
    }

    @Override
    public void queryCity(int provinceId) {
        Observable.just(
                DataSupport.where("provinceid = ?", String.valueOf(provinceId))
                        .find(City.class)
        )
                .flatMap(cityList0 -> {
                    if (cityList0 == null || cityList0.size() == 0) {
                        if (NetWorkUtil.isNetworkConnected(CoolWeatherApp.getContext())) {
                            return RetrofitUtil.create(CityApi.class)
                                    .query(provinceId)
                                    .map(cityList12 ->
                                            ModelUtil.addProvinceIdToCity(provinceId, cityList12)
                                    )
                                    .flatMap(cityList1 -> {
                                        DataSupport.saveAll(cityList1);
                                        return Observable.fromArray(cityList1);
                                    });
                        }
                        chooseAreaView.networkErrorView();
                    }
                    return Observable.fromArray(cityList0);
                })
                .compose(RxSchedulersUtil.ioToMain())
                .compose(RxLifecycleAndroid.bindFragment(subject))
                .subscribe(
                        cityList -> {
                            chooseAreaView.loadCityData(cityList);
                        }, throwable ->
                                Logger.e(throwable, "cityError")
                );

    }

    @Override
    public void queryCounty(int provinceId, int cityId) {
        Observable.just(
                DataSupport.where("cityid = ?", String.valueOf(cityId))
                        .find(County.class)
        )
                .flatMap(cityList -> {
                    if (cityList == null || cityList.size() == 0) {
                        if (NetWorkUtil.isNetworkConnected(CoolWeatherApp.getContext())) {
                            return RetrofitUtil.create(CountyApi.class)
                                    .query(provinceId, cityId)
                                    .map(counties -> ModelUtil.addCityIdToCounty(cityId, counties))
                                    .flatMap(countyList1 -> {
                                        DataSupport.saveAll(countyList1);
                                        return Observable.fromArray(countyList1);
                                    });
                        }
                        chooseAreaView.networkErrorView();
                    }
                    return Observable.fromArray(cityList);
                })
                .compose(RxSchedulersUtil.ioToMain())
                .compose(RxLifecycleAndroid.bindFragment(subject))
                .subscribe(countyList -> {
                            chooseAreaView.loadCountyData(countyList);
                        }, throwable ->
                                Logger.e(throwable, "cityError")
                );

    }

}
