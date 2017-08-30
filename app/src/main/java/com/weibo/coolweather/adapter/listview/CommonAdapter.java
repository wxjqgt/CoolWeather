package com.weibo.coolweather.adapter.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by weixj on 2017/8/30.
 */

public abstract class CommonAdapter<T> extends BaseAdapter {

    private List<T> dataList;
    private Context context;
    private int layoutId;

    public CommonAdapter(Context context, int layoutId, List<T> dataList) {
        this.dataList = dataList;
        this.context = context;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return dataList.size() != 0 ? dataList.size() : 0;
    }

    @Override
    public T getItem(int position) {
        return dataList.get(position) != null ? dataList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(layoutId,parent,false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        convert(holder,dataList.get(position),position);
        return view;
    }

    public abstract void convert(ViewHolder holder, T item, int position);
}
