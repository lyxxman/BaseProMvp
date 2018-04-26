package com.ly.baseproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ly.baseproject.base.BaseActivity;
import com.ly.baseproject.net.ResponeThrowable;
import com.ly.baseproject.presenter.MainActivityPresenter;
import com.ly.baseproject.presenter.impl.MainActivityPresenterImpl;
import com.ly.baseproject.view.MainView;

public class MainActivity extends BaseActivity<MainView,MainActivityPresenterImpl> implements MainView, View.OnClickListener {

    private TextView mTxtClick;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTxtClick = findViewById(R.id.txt_clicek);
        mTxtClick.setOnClickListener(this);
    }

    @Override
    protected MainActivityPresenterImpl initPresenter() {
        return new MainActivityPresenterImpl();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onClick(View view) {
        presenter.getAreas();
    }

    @Override
    public void onGetAreaSuccess(String str) {
            mTxtClick.setText(""+str);
    }

    @Override
    public void onGetAreaError(ResponeThrowable responeThrowable) {

    }
}
