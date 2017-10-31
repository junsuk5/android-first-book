package com.suwonsmartapp.quickdustinfo.util;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by junsuk on 2017. 9. 14..
 */

public class GeoUtil {

    public interface GeoUtilListener {
        void onSuccess(double lat, double lng);

        void onError(String message);
    }

    public static void getLocationFromName(Context context, final String city, final GeoUtilListener listener) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());

        List<Address> addresses = new ArrayList<>();
        try {
            addresses = geocoder.getFromLocationName(city, 1);
            if (addresses.size() > 0) {
                double lat = addresses.get(0).getLatitude();
                double lng = addresses.get(0).getLongitude();
                listener.onSuccess(lat, lng);
            } else {
                listener.onError("주소 결과가 없습니다");
            }
        } catch (IOException e) {
            listener.onError(e.getMessage());
        }
    }

}
