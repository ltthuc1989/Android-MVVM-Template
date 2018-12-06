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
public class ViewportBean {
    @SerializedName("northeast")
    private NortheastBean northeast;
    @SerializedName("southwest")
    private SouthwestBean southwest;

    public static ViewportBean objectFromData(String str) {

        return new Gson().fromJson(str, ViewportBean.class);
    }

    public static ViewportBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), ViewportBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<ViewportBean> arrayViewportBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<ViewportBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<ViewportBean> arrayViewportBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<ViewportBean>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public NortheastBean getNortheast() {
        return northeast;
    }

    public void setNortheast(NortheastBean northeast) {
        this.northeast = northeast;
    }

    public SouthwestBean getSouthwest() {
        return southwest;
    }

    public void setSouthwest(SouthwestBean southwest) {
        this.southwest = southwest;
    }
}
