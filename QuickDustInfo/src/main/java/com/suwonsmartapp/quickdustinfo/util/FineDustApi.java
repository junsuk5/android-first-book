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
    String BASE_URL = "http://api.weatherplanet.co.kr/";

    @Headers("appKey: 6b200e091d1a4d7e83fb9b4732809b33")
    // 쿼리
    @GET("weather/dust?version=1")
    Call<FineDust> getFineDust(@Query("lat") double latitude,
                               @Query("lon") double longitude);
}
