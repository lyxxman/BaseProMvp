package com.ly.baseproject.net.exception;

/**
 * @author ly
 * @version V1.0
 * @Package com.ly.baseproject.net.exception
 * @Description: TODO(请输入一段描述)
 * @date 2018/4/26 14:24
 */

public class ServerException extends RuntimeException {
    private  int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}