package com.weibo.coolweather.util;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by weibo on 2017/9/8.
 */

public class ImageLoader {
    public static void load(Context context, String imageUrl, ImageView imageView) {
        Picasso.with(context).load(imageUrl).into(imageView);
    }
}
