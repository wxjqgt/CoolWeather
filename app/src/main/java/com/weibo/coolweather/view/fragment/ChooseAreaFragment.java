package com.weibo.coolweather.view.fragment;

import android.app.ProgressDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.trello.rxlifecycle2.android.FragmentEvent;
import com.weibo.coolweather.R;
import com.weibo.coolweather.adapter.listview.CommonAdapter;
import com.weibo.coolweather.adapter.listview.ViewHolder;
import com.weibo.coolweather.model.Area;
import com.weibo.coolweather.model.db.City;
import com.weibo.coolweather.model.db.Province;
import com.weibo.coolweather.presenter.BasePresenter;
import com.weibo.coolweather.presenter.ChooseAreaPresenterImp;
import com.weibo.coolweather.presenter.contract.ChooseAreaContract;
import com.weibo.coolweather.util.ModelUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnItemClick;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by weixj on 2017/8/29.
 */

public class ChooseAreaFragment extends BaseFragment implements ChooseAreaContract.ChooseAreaView {

    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.back_button)
    Button backButton;
    @BindView(R.id.listView)
    ListView listView;

    private ProgressDialog progressDialog;
    private CommonAdapter<Area> adapter;
    //选中的省份
    private Province selectProvince;
    //选中的城市
    private City selectCity;
    //当前选中的级别
    private int currentLevel;

    private ChooseAreaContract.ChooseAreaPresenter chooseAreaPresenter;

    @Override
    protected void loadData() {
        new ChooseAreaPresenterImp(this);
        chooseAreaPresenter.queryProvince();
    }

    @Override
    public void loadProvinceData(List<Province> provinceList) {
        titleText.setText("中国");
        adapter = new CommonAdapter<Area>(
                context,
                R.layout.choose_area_item,
                ModelUtil.convert(provinceList)
        ) {
            @Override
            public void convert(ViewHolder holder, Area area, int position) {
                holder.setText(R.id.areaName, area.getName());
            }
        };
        listView.setAdapter(adapter);
    }

    @OnItemClick(R.id.listView)
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public BehaviorSubject<FragmentEvent> lifecycle() {
        return lifecycleSubject;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.choose_area;
    }

    @Override
    public void setPresenter(BasePresenter presenter) {
        chooseAreaPresenter = (ChooseAreaContract.ChooseAreaPresenter) presenter;
    }

}
