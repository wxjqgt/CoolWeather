package com.weibo.coolweather.adapter.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weixj on 2017/8/30.
 */

public abstract class CommonAdapter<T> extends BaseAdapter {

    private List<T> dataList;
    private Context context;
    private int layoutId;

    public CommonAdapter(Context context, int layoutId, List<T> dataList) {
        this.dataList = new ArrayList<>();
        this.dataList.addAll(dataList);
        this.context = context;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public T getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(layoutId, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        convert(holder, dataList.get(position), position);
        return view;
    }

    public void addData(List<T> list) {
        dataList.addAll(list);
        notifyDataSetChanged();
    }

    public void setData(List<T> dataList) {
        this.dataList.clear();
        addData(dataList);
    }

    public abstract void convert(ViewHolder holder, T item, int position);
}
