package com.weibo.coolweather.model.db;

import com.weibo.coolweather.model.Area;

/**
 * Created by weixj on 2017/8/29.
 */

public class Province extends Area {

    private String provinceName;
    private int provinceCode;

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }
}
