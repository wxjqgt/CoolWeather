package com.weibo.coolweather.model;

import org.litepal.crud.DataSupport;

/**
 * Created by weixj on 2017/8/30.
 */

public class Area extends DataSupport{
    private String name;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
