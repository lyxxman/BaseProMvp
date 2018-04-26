package com.ly.baseproject.service;

import com.ly.baseproject.bean.BaseResponse;
import com.ly.baseproject.bean.GeoArea;

import java.util.ArrayList;

import retrofit2.http.GET;
import rx.Observable;

/**
 * @author ly
 * @version V1.0
 * @Package com.ly.baseproject.service
 * @Description: TODO(请输入一段描述)
 * @date 2018/4/26 14:01
 */

public interface AreaService {

    @GET("geoarea/getAllGeoEnable")
    Observable<BaseResponse<ArrayList<GeoArea>>> getAllEnableGeo();

}
