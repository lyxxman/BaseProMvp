package com.ly.baseproject.net.retorfit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ly.baseproject.base.AppConfig;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author ly
 * @version V1.0
 * @Package com.tangjiutoutiao.net.retrofit
 * @Description: 生成retrofit对象
 * @date 2017/11/21 17:14
 */

public class NetRetrofit2 {
    private static NetRetrofit2 instance = null;
    private static Retrofit retrofit = null;

    private NetRetrofit2() {
    }

    public static NetRetrofit2 instance() {
        if (instance == null) {
            instance = new NetRetrofit2();
        }
        return instance;
    }

    public Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = createRetrofit();
        }
        return retrofit;
    }

    /**
     * 创建retrofit
     *
     * @return
     */
    private Retrofit createRetrofit() {
        Gson gson = new GsonBuilder()
                //配置你的Gson
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .client(OkHttpClient3.intance().getOkHttpClient())
                .baseUrl(AppConfig.ServiceAddress + "/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit;
    }

    /**
     * 更新rerofit对象
     */
    public void updateRetrofit() {
        retrofit = null;
//        retrofit = createRetrofit();
    }
}
