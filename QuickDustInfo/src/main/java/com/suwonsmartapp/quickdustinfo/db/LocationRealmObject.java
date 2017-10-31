package com.suwonsmartapp.quickdustinfo.db;

import io.realm.RealmObject;

/**
 * Created by junsuk on 2017. 9. 11..
 */

public class LocationRealmObject extends RealmObject {
    private String name;
    private double lat;
    private double lng;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
