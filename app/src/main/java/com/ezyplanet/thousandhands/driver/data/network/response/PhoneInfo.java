package com.ezyplanet.thousandhands.driver.data.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by nguyenhuutinh on 8/31/17.
 */

public class PhoneInfo {

    @SerializedName("country_code")
    @Expose
    public String country_code;
    @SerializedName("national")
    @Expose
    public String national;
}
