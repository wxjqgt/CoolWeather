package com.weibo.coolweather.util;

import com.weibo.coolweather.model.Area;
import com.weibo.coolweather.model.db.City;
import com.weibo.coolweather.model.db.County;
import com.weibo.coolweather.model.db.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weixj on 2017/8/30.
 */

public final class ModelUtil {

    public static List<Area> convertProvince(List<Province> provinceList) {
        List<Area> areaList = new ArrayList<>();
        for (Province p : provinceList) {
            areaList.add(p);
        }
        return areaList;
    }

    public static List<Province> addProvinceCodeAndName(List<Province> provinces) {
        List<Province> provinceList = new ArrayList<>();
        for (Province province : provinces) {
            province.setProvinceCode(province.getId());
            province.setProvinceName(province.getName());
            provinceList.add(province);
            provinceList.add(province);
        }
        return provinceList;
    }

    public static List<Area> convertCity(List<City> cityList) {
        List<Area> areaList = new ArrayList<>();
        for (City city : cityList) {
            areaList.add(city);
        }
        return areaList;
    }

    public static List<City> addProvinceIdToCity(int provinceId,List<City> citys) {
        List<City> cityList = new ArrayList<>();
        for (City city : citys) {
            city.setCityCode(city.getId());
            city.setCityName(city.getName());
            city.setProvinceId(provinceId);
            cityList.add(city);
        }
        return cityList;
    }

    public static List<Area> convertCounty(List<County> countyList) {
        List<Area> areaList = new ArrayList<>();
        for (County county : countyList) {
            areaList.add(county);
        }
        return areaList;
    }


    public static List<County> addCityIdToCounty(int cityId,List<County> countys) {
        List<County> countyList = new ArrayList<>();
        for (County county : countys) {
            county.setCityId(cityId);
            county.setCountyName(county.getName());
            countyList.add(county);
        }
        return countyList;
    }

}
