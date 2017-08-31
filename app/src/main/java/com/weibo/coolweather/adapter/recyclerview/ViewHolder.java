package com.weibo.coolweather.adapter.recyclerview;

import android.content.Context;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/7/9.
 */
public class ViewHolder extends RecyclerView.ViewHolder {

    private View itemView;
    private Context context;

    private SparseArrayCompat<View> views = new SparseArrayCompat<>();

    public ViewHolder(View itemView, Context context) {
        super(itemView);
        this.itemView = itemView;
        this.context = context;
    }

    public static ViewHolder createViewHolder(Context context, View itemView) {
        return (new ViewHolder(itemView, context));
    }

    public static ViewHolder createViewHolder(Context context, int LaoutId, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(LaoutId, parent, false);
        return (new ViewHolder(view, context));
    }

    public <T extends View> T getView(int id) {
        View view = views.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
            views.put(id, view);
        }
        return (T) view;
    }

    public void setText(int id, String text) {
        ((TextView) getView(id)).setText(text);
    }

    public void setImage(int id, int res) {
        ((ImageView) getView(id)).setImageResource(res);
    }

}
