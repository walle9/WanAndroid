package com.example.wanandroid.model.http;

import com.example.wanandroid.R;
import com.example.wanandroid.base.MyApplication;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

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
     * 从其他线程转到主线程
     *
     * @param scheduler Schedulers.io()等等
     * @param <T>       t
     */

    public static <T> ObservableTransformer<T, T> toMainOfObservable(Scheduler scheduler) {

        return upstream -> upstream.subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread
                ());
    }

    public static <T> FlowableTransformer<T, T> toMainOfFlowable(Scheduler scheduler) {
        return upstream -> upstream.subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 从其他线程转到主线程,处理HttpNoResul,抛出自定义异常
     *
     * @param scheduler
     * @return
     */
    public static ObservableTransformer<HttpNoResult, String> ToMainHandlerHttpNoResultObservable(Scheduler scheduler) {
        return upstream -> upstream.subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread()).flatMap(tHttpNoResult -> {
            if (tHttpNoResult.getCode() == ErrorInfo.SUCCESS) {
                return Observable.just(MyApplication.getInstance().getString(R.string.successful));
            } else {
                return Observable.error(new DefinedException(tHttpNoResult.getCode(),
                        tHttpNoResult.getErrorMsg()));
            }
        });

    }

    /**
     * 从其他线程转到主线程,处理HttpResul,抛出自定义异常
     *
     * @param scheduler Schedulers.io()等等
     * @param <T>       t
     * @return
     */
    public static <T> ObservableTransformer<HttpResult<T>, T> ToMainHandlerHttpResultObservable(Scheduler scheduler) {
        return upstream -> upstream.subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread()).flatMap(tHttpResult -> {
            if (tHttpResult.getCode() == ErrorInfo.SUCCESS) {
                //return createDataOfObservable(tHttpResult.data);
                return Observable.just(tHttpResult.getData());
            } else {
                return Observable.error(new DefinedException(tHttpResult.getCode(),
                        tHttpResult.getMsg()));
            }

        });
    }


    public static <T> FlowableTransformer<HttpResult<T>, T> ToMainHandlerHttpResultFlowable(Scheduler scheduler) {
        return upstream -> upstream.subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread()).flatMap(tHttpResult -> {

            if (tHttpResult.getCode() == ErrorInfo.SUCCESS) {
                return Flowable.just(tHttpResult.getData());
            } else {
                return Flowable.error(new DefinedException(tHttpResult.getCode(),
                        tHttpResult.getMsg()));
            }

        });
    }

    public static FlowableTransformer<HttpNoResult, String> ToMainHandlerHttpNoResultFlowable(Scheduler scheduler) {
        return upstream -> upstream.subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread()).flatMap(tHttpNoResult -> {
            if (tHttpNoResult.getCode() == ErrorInfo.SUCCESS) {
                return Flowable.just(MyApplication.getInstance().getString(R.string.successful));
            } else {
                return Flowable.error(new DefinedException(tHttpNoResult.getCode(),
                        tHttpNoResult.getErrorMsg()));
            }
        });
    }


    /**
     * 生成Flowable
     *
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
     *
     * @param data
     * @param <T>
     * @return
     */
    private static <T> Observable<T> createDataOfObservable(final T data) {
        return Observable.create(e -> {
            e.onNext(data);
            e.onComplete();
        });
    }


}
