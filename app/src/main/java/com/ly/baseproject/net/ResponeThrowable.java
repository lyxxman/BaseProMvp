package com.ly.baseproject.net;

/**
 * @author ly
 * @version V1.0
 * @Package com.ly.baseproject.net
 * @Description: TODO(请输入一段描述)
 * @date 2018/4/26 14:23
 */

public class ResponeThrowable extends Exception {
    public int code;
    public String message;

    public ResponeThrowable(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }
}
