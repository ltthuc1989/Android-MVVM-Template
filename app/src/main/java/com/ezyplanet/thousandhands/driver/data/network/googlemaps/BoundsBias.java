package com.ezyplanet.thousandhands.driver.data.network.googlemaps;

/**
 * Created by Thuc on 6/2/2016.
 */
public class BoundsBias {
    private double sw_lat=0;
    private double sw_lng=0;
    private double ne_lat=0;
    private double ne_lng=0;

    public double getSw_lat() {
        return sw_lat;
    }

    public void setSw_lat(double sw_lat) {
        this.sw_lat = sw_lat;
    }

    public double getSw_lng() {
        return sw_lng;
    }

    public void setSw_lng(double sw_lng) {
        this.sw_lng = sw_lng;
    }

    public double getNe_lat() {
        return ne_lat;
    }

    public void setNe_lat(double ne_lat) {
        this.ne_lat = ne_lat;
    }

    public double getNe_lng() {
        return ne_lng;
    }

    public void setNe_lng(double ne_lng) {
        this.ne_lng = ne_lng;
    }
}
