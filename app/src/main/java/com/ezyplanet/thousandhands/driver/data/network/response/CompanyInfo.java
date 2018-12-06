package com.ezyplanet.thousandhands.driver.data.network.response;

/**
 * Created by NguyenHuuTinh on 11/22/16.
 */
public class CompanyInfo {
    private String hotline;
    private String email;
    private String name;
    String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHotline() {
        return hotline;
    }

    public void setHotline(String hotline) {
        this.hotline = hotline;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
