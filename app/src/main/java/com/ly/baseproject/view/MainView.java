package com.ly.baseproject.view;

import com.ly.baseproject.net.ResponeThrowable;

/**
 * @author ly
 * @version V1.0
 * @Package com.ly.baseproject.view
 * @Description: 主页视图
 * @date 2018/4/12 16:50
 */

public interface MainView {

    void onGetAreaSuccess(String str);

    void onGetAreaError(ResponeThrowable responeThrowable);
}
