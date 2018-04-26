package com.ly.baseproject.presenter;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import rx.Observable;
import rx.subscriptions.CompositeSubscription;

/**
 * @author ly
 * @version V1.0
 * @Package com.ly.baseproject.presenter
 * @Description: 基础presenter类
 * @date 2018/4/12 16:32
 */

public abstract class BasePresenter<T> {

    //view接口类型的弱引用
    protected Reference<T> mViewRef;
    //用于统一取消未完成的订阅
    protected CompositeSubscription mCompositeSubscription;

    public void attachView(T view) {
        mCompositeSubscription = new CompositeSubscription();
        mViewRef = new WeakReference<T>(view);//建立关联
    }

    protected T getView() {
        return mViewRef.get();
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    public void detachView() {
        //取消还存在的订阅
        if (mCompositeSubscription != null && mCompositeSubscription.isUnsubscribed()) {
            mCompositeSubscription.unsubscribe();
            mCompositeSubscription = null;
        }
        //及时销毁P层
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }
}
