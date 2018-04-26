package com.ly.baseproject.presenter.impl;

import com.ly.baseproject.bean.BaseResponse;
import com.ly.baseproject.bean.GeoArea;
import com.ly.baseproject.net.CommonSubscriber;
import com.ly.baseproject.net.ExceptionHandle;
import com.ly.baseproject.net.ResponeThrowable;
import com.ly.baseproject.net.retorfit.NetRetrofit2;
import com.ly.baseproject.presenter.BasePresenter;
import com.ly.baseproject.presenter.MainActivityPresenter;
import com.ly.baseproject.service.AreaService;
import com.ly.baseproject.view.MainView;

import java.util.ArrayList;

import rx.Observable;
import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * @author ly
 * @version V1.0
 * @Package com.ly.baseproject.presenter.impl
 * @Description: 首页测试
 * @date 2018/4/12 16:51
 */

public class MainActivityPresenterImpl extends BasePresenter<MainView> implements MainActivityPresenter {

    private AreaService areaService;

    public MainActivityPresenterImpl() {
        areaService = NetRetrofit2.instance().getRetrofit().create(AreaService.class);
    }

    @Override
    public void getAreas() {
        Observable<BaseResponse<ArrayList<GeoArea>>> observable = areaService.getAllEnableGeo();
        Subscription subscription = observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CommonSubscriber<BaseResponse<ArrayList<GeoArea>>>() {
                    @Override
                    public void onError(ResponeThrowable responeThrowable) {
                        getView().onGetAreaError(responeThrowable);
                    }

                    @Override
                    public void onNext(BaseResponse<ArrayList<GeoArea>> arrayListBaseResponse) {
                        if (arrayListBaseResponse.isOk()) {
                            getView().onGetAreaSuccess(arrayListBaseResponse.getData().toString());
                        } else {
                            onError(new ResponeThrowable(new Throwable(arrayListBaseResponse.getMessage()), arrayListBaseResponse.getStatusCode()));
                        }
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void detachView() {
        super.detachView();
        areaService = null;
    }
}
