package com.ly.baseproject.base;

import android.app.Application;

/**
 * @author ly
 * @version V1.0
 * @Package com.ly.baseproject
 * @Description: TODO(请输入一段描述)
 * @date 2018/4/12 16:46
 */

public class BaseApplication extends Application {
    private static BaseApplication sIntance;

    @Override
    public void onCreate() {
        super.onCreate();
        sIntance = this;
    }
    /**
     * 获得application单例
     *
     * @return
     */
    public static synchronized BaseApplication getInstance() {
        return sIntance;
    }
}
