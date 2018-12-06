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
public class GeometryBean {
    @SerializedName("location")
    private LocationBean location;
    @SerializedName("viewport")
    private ViewportBean viewport;

    public static GeometryBean objectFromData(String str) {

        return new Gson().fromJson(str, GeometryBean.class);
    }

    public static GeometryBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), GeometryBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<GeometryBean> arrayGeometryBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<GeometryBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<GeometryBean> arrayGeometryBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<GeometryBean>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public LocationBean getLocation() {
        return location;
    }

    public void setLocation(LocationBean location) {
        this.location = location;
    }

    public ViewportBean getViewport() {
        return viewport;
    }

    public void setViewport(ViewportBean viewport) {
        this.viewport = viewport;
    }
}
