package com.suwonsmartapp.quickdustinfo.util;


import com.suwonsmartapp.quickdustinfo.model.FineDust;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by junsuk on 2017. 9. 7..
 */

public interface FineDustApi {
    String BASE_URL = "http://apis.skplanetx.com/";

    @Headers("appKey: d39d6ed5-38b2-3205-b7f2-db02ea0ecf3a")
    // 쿼리
    @GET("weather/dust?version=1")
    Call<FineDust> getFineDust(@Query("lat") double latitude,
                               @Query("lon") double longitude);
}
