package com.weibo.coolweather.presenter;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;
import com.weibo.coolweather.Constant;
import com.weibo.coolweather.api.ProvinceApi;
import com.weibo.coolweather.model.db.City;
import com.weibo.coolweather.model.db.County;
import com.weibo.coolweather.model.db.Province;
import com.weibo.coolweather.presenter.contract.ChooseAreaContract;

import org.litepal.crud.DataSupport;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by weixj on 2017/8/30.
 */

public class ChooseAreaPresenterImp implements ChooseAreaContract.ChooseAreaPresenter {

    private ChooseAreaContract.ChooseAreaView chooseAreaView;
    private BehaviorSubject<FragmentEvent> subject;
    //省列表
    private List<Province> provinceList;
    //市列表
    private List<City> cityList;
    //县列表
    private List<County> countyList;

    public ChooseAreaPresenterImp(BaseView baseView) {
        this.chooseAreaView = (ChooseAreaContract.ChooseAreaView) baseView;
        baseView.setPresenter(this);
        subject = chooseAreaView.lifecycle();
    }

    @Override
    public void queryProvince() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.URL.WEATHER_API_URL)
                .build();
        Observable.just(DataSupport.findAll(Province.class))
                .flatMap(provinceList -> {
                    Logger.d(provinceList);
                    if (provinceList != null && provinceList.size() > 0) {
                        return Observable.fromArray(provinceList);
                    } else {
                        return retrofit.create(ProvinceApi.class).queryPorvince();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleAndroid.bindFragment(subject))
                .subscribe(provinces -> {
                    provinceList = provinces;
                    chooseAreaView.loadProvinceData(provinceList);
                    DataSupport.saveAllAsync(provinces);
                });
    }

}
