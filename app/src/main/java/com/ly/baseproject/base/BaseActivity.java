package com.ly.baseproject.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.ly.baseproject.presenter.BasePresenter;

/**
 * @author ly
 * @version V1.0
 * @Package com.ly.baseproject
 * @Description: 自定义基础base activity类
 * @date 2018/4/12 16:44
 */

public abstract class BaseActivity<V, T extends BasePresenter<V>> extends FragmentActivity {
    protected T presenter;

    @SuppressWarnings(value = {"unchecked"})
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = initPresenter();
        presenter.attachView((V) this);
    }

    protected abstract T initPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        presenter = null;
    }
}
