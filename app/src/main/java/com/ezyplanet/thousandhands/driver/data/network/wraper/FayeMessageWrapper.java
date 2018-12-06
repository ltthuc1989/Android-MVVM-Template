package com.ezyplanet.thousandhands.driver.data.network.wraper;

import com.google.gson.annotations.SerializedName;

/**
 * Created by misotomovski on 11/3/15.
 */
public class FayeMessageWrapper<T> {
    @SerializedName("event_name")
    private String eventName;

    private T data;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
