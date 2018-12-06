package com.ezyplanet.thousandhands.driver.data.network.response;

/**
 * Created by ezyplanet on 9/13/16.
 */
public class DisableResp {

    /**
     * id : 4
     * user_id : 2
     * token : abc
     * client : android
     * device_name : HTC One
     * latitude : 10.802007
     * longitude : 106.705174
     * disabled_at : 2016-09-06T10:54:50.191Z
     * created_at : 2015-08-09T14:38:39.418Z
     * updated_at : 2015-08-09T14:38:39.418Z
     */

    private String id;
    private String user_id;
    private String token;
    private String client;
    private String device_name;
    private String latitude;
    private String longitude;
    private String disabled_at;
    private String created_at;
    private String updated_at;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDisabled_at() {
        return disabled_at;
    }

    public void setDisabled_at(String disabled_at) {
        this.disabled_at = disabled_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
