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

public class PlaceDetail {

    @SerializedName("result")
    private ResultBean result;
    @SerializedName("status")
    private String status;
    @SerializedName("html_attributions")
    private List<String> htmlAttributions;

    public static PlaceDetail objectFromData(String str) {

        return new Gson().fromJson(str, PlaceDetail.class);
    }

    public static PlaceDetail objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), PlaceDetail.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<PlaceDetail> arrayPlaceDetailFromData(String str) {

        Type listType = new TypeToken<ArrayList<PlaceDetail>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<PlaceDetail> arrayPlaceDetailFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<PlaceDetail>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getHtmlAttributions() {
        return htmlAttributions;
    }

    public void setHtmlAttributions(List<String> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
    }
}
