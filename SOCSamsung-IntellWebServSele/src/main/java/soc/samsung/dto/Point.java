package soc.samsung.dto;

import com.google.gson.Gson;

public class Point {
    private float longitude;
    private float latitude;

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = longitude;
    }

    public float getLongitude() {
        return longitude;

    }

    public float getLatitude() {
        return latitude;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

