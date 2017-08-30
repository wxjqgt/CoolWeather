package com.weibo.coolweather.presenter;

import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;
import com.weibo.coolweather.api.ProvinceApi;
import com.weibo.coolweather.model.db.City;
import com.weibo.coolweather.model.db.County;
import com.weibo.coolweather.model.db.Province;
import com.weibo.coolweather.presenter.contract.ChooseAreaContract;
import com.weibo.coolweather.util.RetrofitUtil;
import com.weibo.coolweather.util.RxSchedulersUtil;

import org.litepal.crud.DataSupport;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

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

        Observable.just(DataSupport.findAll(Province.class))
                .flatMap(provinceList -> {
                    if (provinceList != null && provinceList.size() > 0) {
                        return Observable.fromArray(provinceList);
                    } else {
                        return RetrofitUtil.create(ProvinceApi.class)
                                .queryPorvince()
                                .flatMap(provinceList1 -> {
                                    DataSupport.saveAllAsync(provinceList1);
                                    return Observable.fromArray(provinceList1);
                                });
                    }
                })
                .compose(RxSchedulersUtil.ioToMain())
                .compose(RxLifecycleAndroid.bindFragment(subject))
                .subscribe(provinces -> {
                    provinceList = provinces;
                    chooseAreaView.loadProvinceData(provinceList);
                });
    }

}
