package com.example.wanandroid.model.http;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @ProjectName: MyMvpDemo
 * @Package: com.example.wanandroid.model.http
 * @ClassName: MyRxUtils
 * @Description: RX切换线程的工具类
 * @Author: walle
 * @CreateDate: 2019/8/19 20:43
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/8/19 20:43
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class MyRxUtils {

    /**
     * 从其他线程转到主线程.
     *
     * @param scheduler Schedulers.io()等等
     * @param <T>       t
     */

    public static <T> ObservableTransformer<T, T> toMainOfObservableTransformer(Scheduler scheduler) {

        return upstream -> upstream.subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 从其他线程转到主线程,增加抛出自定义异常
     * @param scheduler Schedulers.io()等等
     * @param <T>   t
     * @return
     */
    public static <T> ObservableTransformer<HttpResult<T>, T> ToMainWithHandResultOfObservableTransformer(Scheduler scheduler) {
        return upstream -> upstream.subscribeOn(scheduler)
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(tHttpResult -> {
                    if (!tHttpResult.isError()) {
                        return /*createData(tHttpResult.data)*/Observable.just(tHttpResult.getData());
                    } else {
                        return Observable.error(new ServerDefinedException(Integer.valueOf(tHttpResult.getCode()),tHttpResult.getMsg()));
                    }
                });
    }



    public static <T> FlowableTransformer<T, T> toMainOfFlowable(Scheduler scheduler) {
        return upstream -> upstream.subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> FlowableTransformer<HttpResult<T>, T> toMainWithHandResultOfFlowable(Scheduler scheduler) {
        return upstream -> upstream.subscribeOn(scheduler)
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(tHttpResult -> {
                    if (ErrorInfo.SUCCESS.equals(tHttpResult.getCode())) {
                        return /*createData(tHttpResult.data)*/Flowable.just(tHttpResult.getData());
                    } else {
                        return Flowable.error(new ServerDefinedException(Integer.valueOf(tHttpResult.getCode()),tHttpResult.getMsg()));
                    }
                });
    }



    /**
     * 生成Flowable
     * @param data
     * @param <T>
     * @return
     */
    private static <T> Flowable<T> createDataOfFlowable(final T data) {
        return Flowable.create(e -> {
            e.onNext(data);
            e.onComplete();
        }, BackpressureStrategy.ERROR);
    }

    /**
     * 生成Observable
     * @param data
     * @param <T>
     * @return
     */
    private static <T> Observable<T> createData(final T data) {
        return Observable.create(e -> {
            e.onNext(data);
            e.onComplete();
        });
    }


}
