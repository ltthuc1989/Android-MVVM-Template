package com.ezyplanet.thousandhands.driver.data.network.response;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PV on 4/14/2017.
 */

public class CompanyBean {
    @SerializedName("id")
    private int idX;
    @SerializedName("trading_name")
    private String tradingName;
    @SerializedName("abn")
    private String abn;
    @SerializedName("trades_license")
    private String tradesLicense;
    @SerializedName("contact_name")
    private String contactName;
    @SerializedName("address")
    private String address;
    @SerializedName("contact_number")
    private String contactNumber;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;

    public static CompanyBean objectFromData(String str) {

        return new Gson().fromJson(str, CompanyBean.class);
    }

    public static CompanyBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), CompanyBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<CompanyBean> arrayCompanyBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<CompanyBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<CompanyBean> arrayCompanyBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<CompanyBean>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public int getIdX() {
        return idX;
    }

    public void setIdX(int idX) {
        this.idX = idX;
    }

    public String getTradingName() {
        return tradingName;
    }

    public void setTradingName(String tradingName) {
        this.tradingName = tradingName;
    }

    public String getAbn() {
        return abn;
    }

    public void setAbn(String abn) {
        this.abn = abn;
    }

    public String getTradesLicense() {
        return tradesLicense;
    }

    public void setTradesLicense(String tradesLicense) {
        this.tradesLicense = tradesLicense;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
