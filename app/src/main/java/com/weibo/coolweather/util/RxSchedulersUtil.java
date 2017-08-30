package com.weibo.coolweather.util;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by weixj on 2017/8/30.
 */

public final class RxSchedulersUtil {

    private static ObservableTransformer transformer = new ObservableTransformer() {
        @Override
        public ObservableSource apply(Observable upstream) {
            return upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };

    public static <T> ObservableTransformer<T,T> ioToMain(){
        return transformer;
    }

}
