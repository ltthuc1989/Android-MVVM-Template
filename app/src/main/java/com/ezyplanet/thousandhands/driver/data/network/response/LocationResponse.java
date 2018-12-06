package com.ezyplanet.thousandhands.driver.data.network.response;

/**
 * Created by Thuc on 5/12/2016.
 */
public class LocationResponse {



    LocationResponseListener listener;
    public  int currentLangue;
    /**
     * country_code : VN
     * country : Vietnam
     * currency : VND
     * language : vi
     * phone_prefix : 84
     * currency_info : {"symbol":"\u20ab","symbol_first":false,"iso_code":"VND","name":"Vietnamese Đồng","subunit_to_unit":1,"subunit":"Hào"}
     */

    private String country_code;
    private String country;
    private String currency;
    private String language;
    private String phone_prefix;
    /**
     * symbol : ₫
     * symbol_first : false
     * iso_code : VND
     * name : Vietnamese Đồng
     * subunit_to_unit : 1
     * subunit : Hào
     */

    private CurrencyInfoBean currency_info;

    public LocationResponseListener getListener() {
        return listener;
    }




    public void setListener(LocationResponseListener listener){
        this.listener = listener;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPhone_prefix() {
        return phone_prefix;
    }

    public void setPhone_prefix(String phone_prefix) {
        this.phone_prefix = phone_prefix;
    }

    public CurrencyInfoBean getCurrency_info() {
        return currency_info;
    }

    public void setCurrency_info(CurrencyInfoBean currency_info) {
        this.currency_info = currency_info;
    }

    public interface LocationResponseListener{
        void onSuccess(LocationResponse locationResponse);
       void onFail(String message);
    }



    public static class CurrencyInfoBean {
        private String symbol;
        private boolean symbol_first;
        private String iso_code;
        private String name;
        private int subunit_to_unit;
        private String subunit;

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public boolean isSymbol_first() {
            return symbol_first;
        }

        public void setSymbol_first(boolean symbol_first) {
            this.symbol_first = symbol_first;
        }

        public String getIso_code() {
            return iso_code;
        }

        public void setIso_code(String iso_code) {
            this.iso_code = iso_code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSubunit_to_unit() {
            return subunit_to_unit;
        }

        public void setSubunit_to_unit(int subunit_to_unit) {
            this.subunit_to_unit = subunit_to_unit;
        }

        public String getSubunit() {
            return subunit;
        }

        public void setSubunit(String subunit) {
            this.subunit = subunit;
        }
    }
}
