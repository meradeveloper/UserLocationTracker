package com.userlocationtracker;

import java.io.Serializable;

/**
 * Created by Localadmin on 5/11/2018.
 */

public class LocationModel implements Serializable {

    private String Lat;
    private String Long;

    public String getLat() {
        return Lat;
    }

    public void setLat(String lat) {
        Lat = lat;
    }

    public String getLong() {
        return Long;
    }

    public void setLong(String aLong) {
        Long = aLong;
    }

    public void getLatLong() {}
}
