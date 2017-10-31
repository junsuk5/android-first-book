package com.suwonsmartapp.quickdustinfo.model.dust_material;

import java.util.List;

public class Weather {

    private List<Dust> dust;

    public List<Dust> getDust() {
        return dust;
    }

    public void setDust(List<Dust> dust) {
        this.dust = dust;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "dust=" + dust +
                '}';
    }
}