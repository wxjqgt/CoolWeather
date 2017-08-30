package com.weibo.coolweather.adapter.listview;

import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

/**
 * Created by weixj on 2017/8/30.
 */

public class ViewHolder {

    private static SparseArray<View> viewList;
    private View convertView;

    public ViewHolder(View convertView){
        viewList = new SparseArray<>();
        this.convertView = convertView;
    }

    public <T extends View> T getView(int viewId){
        View view = viewList.get(viewId);
        if (view == null){
            view = convertView.findViewById(viewId);
            viewList.append(viewId,view);
        }
        return (T) view;
    }

    public void setText(int viewId,String text){
        TextView textView = getView(viewId);
        textView.setText(text);
    }

}
