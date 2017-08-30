package com.weibo.coolweather.util;

import com.weibo.coolweather.model.Area;
import com.weibo.coolweather.model.db.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weixj on 2017/8/30.
 */

public final class ModelUtil {

    public static List<Area> convert(List<Province> provinceList) {
        List<Area> areaList = new ArrayList<>();
        for (Province p : provinceList) {
            areaList.add(p);
        }
        return areaList;
    }

}
