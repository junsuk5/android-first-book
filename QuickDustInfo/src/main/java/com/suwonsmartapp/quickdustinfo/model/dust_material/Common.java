package com.suwonsmartapp.quickdustinfo.model.dust_material;

public class Common {

    private String alertYn;
    private String stormYn;

    public String getAlertYn() {
        return alertYn;
    }

    public void setAlertYn(String alertYn) {
        this.alertYn = alertYn;
    }

    public String getStormYn() {
        return stormYn;
    }

    public void setStormYn(String stormYn) {
        this.stormYn = stormYn;
    }

    @Override
    public String toString() {
        return "Common{" +
                "alertYn='" + alertYn + '\'' +
                ", stormYn='" + stormYn + '\'' +
                '}';
    }
}