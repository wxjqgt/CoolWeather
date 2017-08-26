package com.weibo.coolweather.view.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * Created by weixj on 2017/8/26.
 */

public abstract class BaseActivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        loadData();
        lisener();
    }

    @LayoutRes protected abstract int getLayoutId();
    protected void initView(){}
    protected void loadData(){}
    protected void lisener(){}

    protected <T extends View> T findView(@IdRes int viewId){
        return (T) findViewById(viewId);
    }

}

