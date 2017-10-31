package com.suwonsmartapp.quickdustinfo.data;


import com.suwonsmartapp.quickdustinfo.util.FineDustUtil;

import retrofit2.Callback;

/**
 * Created by junsuk on 2017. 9. 11..
 */

public class LocationFineDustRepository implements FineDustRepository {

    private FineDustUtil mFineDustUtil;
    private double mLatitude;
    private double mLongitude;

    public LocationFineDustRepository(double lat, double lng) {
        mFineDustUtil = new FineDustUtil();
        mLatitude = lat;
        mLongitude = lng;
    }

    @Override
    public void getFineDustData(Callback callback) {
        mFineDustUtil.getApi().getFineDust(mLatitude, mLongitude).enqueue(callback);
    }

    @Override
    public boolean isAvailable() {
        if (mLatitude != 0.0 && mLongitude != 0.0) {
            return true;

        }
        return false;
    }
}
