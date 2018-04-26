package com.ly.baseproject.net.retorfit;


import com.ly.baseproject.utils.StringUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author ly
 * @version V1.0
 * @Package com.tangjiutoutiao.net.retrofit
 * @Description: 生成okhttpclient
 * @date 2017/11/21 17:20
 */

public class OkHttpClient3 {
    private static OkHttpClient okHttpClient = null;
    private static OkHttpClient3 instance = null;
    private static long TIMEOUT_READ = 15;
    private static long TIMEOUT_CONNECTION = 20;

    public static OkHttpClient3 intance() {
        if (instance == null) {
            instance = new OkHttpClient3();
        }

        return instance;
    }

    public OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            okHttpClient = instance.createOkHttpClient();
        }
        return okHttpClient;
    }

    Interceptor mTokenInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
//            String token = BaseApplication.getInstance().getToken();
//            String deviceId = BaseApplication.getInstance().getDeviceId();
//            if (StringUtil.isEmpty(token) && StringUtil.isEmpty(deviceId)) {
//                return chain.proceed(originalRequest);
//            }
//            if (alreadyHasAuthorizationHeader(originalRequest) && alreadyHasDeviceId(originalRequest)) {
//                return chain.proceed(originalRequest);
//            }
//            Request authorised = originalRequest.newBuilder()
//                    .header("access_token", token)
//                    .header("tjtt_device_id", deviceId)
//                    .build();
            Request authorised = originalRequest.newBuilder()
//                    .header("access_token", token)
//                    .header("tjtt_device_id", deviceId)
                    .build();
            return chain.proceed(authorised);
        }

        private boolean alreadyHasAuthorizationHeader(Request originalRequest) {
            String token = originalRequest.header("access_token");
            if (StringUtil.isEmpty(token)) {
                return false;
            } else
                return true;
        }

        private boolean alreadyHasDeviceId(Request originalRequest) {
            String deviceId = originalRequest.header("tjtt_device_id");
            if (StringUtil.isEmpty(deviceId)) {
                return false;
            } else
                return true;
        }
    };

    private OkHttpClient createOkHttpClient() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(mTokenInterceptor)
                //失败重连
                .retryOnConnectionFailure(true)
                //time out
                .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)
                .build();
        return okHttpClient;
    }

}
