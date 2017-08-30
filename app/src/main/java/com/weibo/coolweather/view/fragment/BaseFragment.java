package com.weibo.coolweather.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.android.FragmentEvent;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by weixj on 2017/8/26.
 */

public abstract class BaseFragment extends Fragment {

    protected View view;
    protected Context context;
    private Unbinder unbinder;

    protected final BehaviorSubject<FragmentEvent> lifecycleSubject = BehaviorSubject.create();

    public BaseFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listener();
    }

    protected void listener(){}

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getUserVisibleHint() && view != null) {
            loadData();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && view != null) {
            loadData();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    protected void loadData() {
    }

    @LayoutRes protected abstract int getLayoutId();


}
