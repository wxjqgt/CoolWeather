package com.weibo.coolweather.view.fragment;

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
import com.weibo.coolweather.adapter.recyclerview.OnRecyclerViewItemClickListener;
import com.weibo.coolweather.adapter.recyclerview.ViewHolder;
import com.weibo.coolweather.model.Area;
import com.weibo.coolweather.model.db.City;
import com.weibo.coolweather.model.db.County;
import com.weibo.coolweather.model.db.Province;
import com.weibo.coolweather.presenter.BasePresenter;
import com.weibo.coolweather.presenter.ChooseAreaPresenterImp;
import com.weibo.coolweather.presenter.contract.ChooseAreaContract;
import com.weibo.coolweather.util.ModelUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by weixj on 2017/8/29.
 */

public class ChooseAreaFragment extends BaseFragment implements ChooseAreaContract.ChooseAreaView {

    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.back_button)
    Button backButton;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private CommonAdapter<Area> adapter;
    //省列表
    private List<Province> provinceList;
    //市列表
    private List<City> cityList;
    private List<County> countyList;
    //当前选中的级别
    String title = "中国";
    private int currentLevel;
    private int currentCityId;
    private int currentCountyId;

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
        recyclerView.scrollToPosition(indexCity(currentCityId));
    }

    private int indexCity(int cityId) {
        int size = provinceList.size();
        for (int i = 0; i < size; i++) {
            if (provinceList.get(i).getId() == cityId) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public void loadCityData(List<City> cityList) {
        this.cityList = cityList;
        adapter.setDatas(ModelUtil.convertCity(cityList));
        recyclerView.scrollToPosition(indexCounty(currentCountyId));
    }

    private int indexCounty(int countyId) {
        int size = countyList.size();
        for (int i = 0; i < size; i++) {
            if (countyList.get(i).getId() == countyId) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public void loadCountyData(List<County> countyList) {
        adapter.setDatas(ModelUtil.convertCounty(countyList));
        recyclerView.scrollToPosition(0);
    }

    @Override
    protected void listener() {
        recyclerView.addOnItemTouchListener(new OnRecyclerViewItemClickListener(recyclerView) {
            @Override
            public void OnItemClickLitener(RecyclerView.ViewHolder viewHolder) {
                if (viewHolder == null) {
                    return;
                }
                int position = viewHolder.getAdapterPosition();
                switch (currentLevel) {
                    case Constant.AREA_LEVEL.LEVEL_PROVINCE:
                        Province province = provinceList.get(position);
                        chooseAreaPresenter.queryCity(province.getId());
                        title = province.getName();
                        currentCityId = province.getProvinceCode();
                        currentLevel = Constant.AREA_LEVEL.LEVEL_CITY;

                        break;
                    case Constant.AREA_LEVEL.LEVEL_CITY:
                        City city = cityList.get(position);
                        chooseAreaPresenter.queryCounty(city.getProvinceId(), city.getCityCode());
                        title = city.getName();
                        currentLevel = Constant.AREA_LEVEL.LEVEL_COUNTY;
                        currentCountyId = city.getCityCode();

                        break;
                    default:
                        break;
                }
                titleText.setText(title);
                backButton.setVisibility(View.VISIBLE);

            }

            @Override
            public void OnItemLongClickLitener(RecyclerView.ViewHolder viewHolder) {
            }
        });
    }

    @OnClick(R.id.back_button)
    public void onClick(View view) {
        switch (currentLevel) {
            case Constant.AREA_LEVEL.LEVEL_CITY:
                backButton.setVisibility(View.GONE);
                chooseAreaPresenter.queryProvince();
                currentLevel = Constant.AREA_LEVEL.LEVEL_PROVINCE;

                break;
            case Constant.AREA_LEVEL.LEVEL_COUNTY:
                chooseAreaPresenter.queryCity(currentCityId);
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
