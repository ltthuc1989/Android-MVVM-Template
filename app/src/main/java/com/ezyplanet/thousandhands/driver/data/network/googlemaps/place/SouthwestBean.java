package com.ezyplanet.thousandhands.driver.data.network.googlemaps.place;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PV on 3/2/2017.
 */
public class SouthwestBean {
    @SerializedName("lat")
    private double lat;
    @SerializedName("lng")
    private double lng;

    public static SouthwestBean objectFromData(String str) {

        return new Gson().fromJson(str, SouthwestBean.class);
    }

    public static SouthwestBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), SouthwestBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<SouthwestBean> arraySouthwestBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<SouthwestBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<SouthwestBean> arraySouthwestBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<SouthwestBean>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


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
