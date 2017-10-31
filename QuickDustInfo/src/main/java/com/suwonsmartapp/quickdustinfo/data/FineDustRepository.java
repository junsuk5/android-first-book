package com.suwonsmartapp.quickdustinfo.data;


import com.suwonsmartapp.quickdustinfo.model.FineDust;

import retrofit2.Callback;

/**
 * Created by junsuk on 2017. 9. 11..
 */

public interface FineDustRepository {

    boolean isAvailable();

    void getFineDustData(Callback<FineDust> callback);
}
