package com.ly.baseproject.net;

import android.content.Context;
import android.util.Log;

import rx.Subscriber;

/**
 * @author ly
 * @version V1.0
 * @Package com.ly.baseproject.net
 * @Description: TODO(请输入一段描述)
 * @date 2018/4/19 11:40
 */

public abstract class CommonSubscriber<T> extends Subscriber<T> {


    public CommonSubscriber() {
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("tag","MySubscriber.onStart()");
        //接下来可以检查网络连接等操作
//        if (!NetworkUtil.isNetworkAvailable(context)) {
//
////            Toast.makeText(context, "当前网络不可用，请检查网络情况", Toast.LENGTH_SHORT).show();
//            // 一定好主动调用下面这一句,取消本次Subscriber订阅
//
//            if (!isUnsubscribed()) {
//                unsubscribe();
//            }
//            return;
//        }
    }

    @Override
    public void onError(Throwable e) {
        Log.e("tag","MySubscriber.throwable ="+e.toString());
        Log.e("tag","MySubscriber.throwable ="+e.getMessage());

        if(e instanceof Exception){
            //访问获得对应的Exception
            onError(ExceptionHandle.handleException(e));
        }else {
            //将Throwable 和 未知错误的status code返回
            onError(new ResponeThrowable(e,ExceptionHandle.ERROR.UNKNOWN));
        }
    }

    public abstract void onError(ResponeThrowable responeThrowable);

    @Override
    public void onCompleted() {
        Log.i("tag","MySubscriber.onComplete()");
    }
}
