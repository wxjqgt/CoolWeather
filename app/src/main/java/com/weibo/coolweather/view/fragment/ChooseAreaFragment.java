package com.weibo.coolweather.view.fragment;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.trello.rxlifecycle2.android.FragmentEvent;
import com.weibo.coolweather.Constant;
import com.weibo.coolweather.R;
import com.weibo.coolweather.adapter.recyclerview.CommonAdapter;
import com.weibo.coolweather.adapter.recyclerview.OnRecyclerViewSimpleItemClickListener;
import com.weibo.coolweather.adapter.recyclerview.ViewHolder;
import com.weibo.coolweather.model.Area;
import com.weibo.coolweather.model.db.City;
import com.weibo.coolweather.model.db.County;
import com.weibo.coolweather.model.db.Province;
import com.weibo.coolweather.presenter.BasePresenter;
import com.weibo.coolweather.presenter.ChooseAreaPresenterImp;
import com.weibo.coolweather.presenter.contract.ChooseAreaContract;
import com.weibo.coolweather.util.ModelUtil;
import com.weibo.coolweather.view.activity.MainActivity;
import com.weibo.coolweather.view.activity.WeatherActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by weixj on 2017/8/29.
 */

public class ChooseAreaFragment extends BaseFragment implements ChooseAreaContract.ChooseAreaView {

    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.back_button)
    Button backButton;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private CommonAdapter<Area> adapter = null;
    //省列表
    private List<Province> provinceList = null;
    //市列表
    private List<City> cityList;
    //县列表
    private List<County> countyList;
    //当前选中的级别
    private Constant.AREA_LEVEL currentLevel;
    private int currentProvinceId;
    private int currrentSelectProvincePosition;
    private int currrentSelectCityPosition;
    private int areaPositionOffset = 4;
    private String currentSelectProvinceName;
    private String title = "中国";

    private ChooseAreaContract.ChooseAreaPresenter chooseAreaPresenter;

    @Override
    protected void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
    }

    @Override
    protected void loadData() {
        new ChooseAreaPresenterImp(this);
        chooseAreaPresenter.queryProvince();
    }

    @Override
    public void loadProvinceData(List<Province> provinceList) {
        this.provinceList = provinceList;
        backButton.setVisibility(View.GONE);
        titleText.setText("中国");
        currentLevel = Constant.AREA_LEVEL.LEVEL_PROVINCE;
        if (adapter == null) {
            createAdapter(ModelUtil.convertProvince(provinceList));
        } else {
            adapter.setDatas(ModelUtil.convertProvince(provinceList));
        }
        recyclerView.scrollToPosition(currrentSelectProvincePosition);
    }

    @Override
    public void loadCityData(List<City> cityList) {
        this.cityList = cityList;
        adapter.setDatas(ModelUtil.convertCity(cityList));
        recyclerView.scrollToPosition(currrentSelectCityPosition);
    }

    @Override
    public void loadCountyData(List<County> countyList) {
        this.countyList = countyList;
        adapter.setDatas(ModelUtil.convertCounty(countyList));
        recyclerView.scrollToPosition(0);
    }

    @Override
    protected void listener() {
        recyclerView.addOnItemTouchListener(new OnRecyclerViewSimpleItemClickListener(recyclerView) {
            @Override
            public void OnItemClickLitener(RecyclerView.ViewHolder viewHolder) {
                if (viewHolder == null) {
                    return;
                }
                int position = viewHolder.getAdapterPosition();
                switch (currentLevel) {
                    case LEVEL_PROVINCE:
                        adapter.clear();
                        Province province = provinceList.get(position);
                        chooseAreaPresenter.queryCity(province.getId());
                        title = province.getName();
                        currentSelectProvinceName = province.getProvinceName();
                        currrentSelectProvincePosition = position + areaPositionOffset;
                        currentProvinceId = province.getProvinceCode();
                        currentLevel = Constant.AREA_LEVEL.LEVEL_CITY;

                        break;
                    case LEVEL_CITY:
                        adapter.clear();
                        City city = cityList.get(position);
                        chooseAreaPresenter.queryCounty(city.getProvinceId(), city.getCityCode());
                        title = city.getName();
                        currrentSelectCityPosition = position + areaPositionOffset;
                        currentLevel = Constant.AREA_LEVEL.LEVEL_COUNTY;

                        break;

                    case LEVEL_COUNTY:
                        County county = countyList.get(position);
                        Intent intent = new Intent(context, WeatherActivity.class);
                        intent.putExtra(Constant.WEATHER_ID, county.getWeather_id());
                        startActivity(intent);
                        ((MainActivity) context).finish();

                        break;
                    default:
                        break;
                }
                titleText.setText(title);
                backButton.setVisibility(View.VISIBLE);

            }
        });
    }

    @OnClick(R.id.back_button)
    public void onClick(View view) {
        switch (currentLevel) {
            case LEVEL_CITY:
                backButton.setVisibility(View.GONE);
                chooseAreaPresenter.queryProvince();
                currentLevel = Constant.AREA_LEVEL.LEVEL_PROVINCE;

                break;
            case LEVEL_COUNTY:
                titleText.setText(currentSelectProvinceName);
                chooseAreaPresenter.queryCity(currentProvinceId);
                currentLevel = Constant.AREA_LEVEL.LEVEL_CITY;

            default:
                break;
        }
    }

    private void createAdapter(List<Area> areaList) {
        adapter = new CommonAdapter<Area>(context, R.layout.choose_area_item, areaList) {
            @Override
            public void convert(ViewHolder holder, Area area, int position) {
                holder.setText(R.id.areaName, area.getName());
            }
        };
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void networkErrorView() {

        Snackbar.make(coordinatorLayout, "当前网络不通！", Snackbar.LENGTH_LONG).show();

    }

    @Override
    public BehaviorSubject<FragmentEvent> getLifecycle() {
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
