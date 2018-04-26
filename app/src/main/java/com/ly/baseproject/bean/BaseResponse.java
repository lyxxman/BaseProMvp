package com.ly.baseproject.bean;

/**
 * @author ly
 * @version V1.0
 * @Title: BaseResponse.java
 * @Package com.blueteam.castwineparty.net
 * @Description: 基础返回类
 * @date 2015年8月5日 上午9:09:42
 */
public class BaseResponse<T> {
    private boolean success;

    private int statusCode;

    private int count;

    private int userTypeId;

    private String statusMessage;

    private String message;
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(int userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isOk() {
        return statusCode == 200;
    }
}
