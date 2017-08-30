package com.weibo.coolweather.util;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by weixj on 2017/8/30.
 */

public final class RxSchedulersUtil {

    private static ObservableTransformer transformer =
            upstream ->
                    upstream.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread());

    public static <T> ObservableTransformer<T, T> ioToMain() {
        return transformer;
    }

}
