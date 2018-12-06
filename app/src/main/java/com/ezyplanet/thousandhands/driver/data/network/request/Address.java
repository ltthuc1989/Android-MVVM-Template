package com.ezyplanet.thousandhands.driver.data.network.request;


import android.os.Parcel;
import android.os.Parcelable;


import com.ezyplanet.thousandhands.driver.data.network.googlemaps.AddressItem;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Thuc on 5/12/2016.
 */
public class Address implements Parcelable {

    /**
     * latitude : 10.7618329
     * longitude : 106.6742248
     */

    private double latitude;
    private double longitude;
    private String city;
    private String country;
    String streetName;
    String note;
    String device_token;
    String route;
    String suburb;
    String state;
    //params for google api
    String urlParams;
    String postal_code;
    String streetNumber;
    float rotation=0;

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    String district;

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setUrlParams(String urlParams) {
        this.urlParams = urlParams;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }


    public String getDevice_token() {
        return device_token;
    }

    public void setDevice_token(String device_token) {
        this.device_token = device_token;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public String getCountry() {
        return country;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
            this.note = note;
    }


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {


        this.city = city;
    }

    public Address() {
    }
    public Address(AddressItem item){
        this.streetNumber = item.getStreetNumber();
        this.route = item.getRoute();
        this.streetName = item.getFormatted_address();
        this.latitude = item.getGeometry().getLocation().getLat();
        this.longitude = item.getGeometry().getLocation().getLng();
        this.city = item.getCity();
        this.country = item.getCountryName();
        this.state = item.getState_2();
        this.postal_code= item.getPostalCode();
        this.suburb = item.getSuburb();
        this.district=item.getState_2();

    }

//    public Address(CartResp.ShipAddressBean item){
//        this.route = item.getRoute();
//        this.streetName = item.getAddress();
//        this.latitude = item.getLatitude();
//        this.longitude = item.getLongitude();
//        this.city = item.getCity();
//        this.country = item.getCountry();
//        this.state = item.getState();
//        this.postal_code= item.getPostal_code();
//        this.suburb = item.getSuburb();
//        this.district=item.getSub_state();
//
//    }
    public void setAddressFrom(AddressItem item){
        this.streetNumber = item.getStreetNumber();
        this.route = item.getRoute();
        this.streetName = item.getFormatted_address();
        this.latitude = item.getGeometry().getLocation().getLat();
        this.longitude = item.getGeometry().getLocation().getLng();
        this.city = item.getCity();
        this.country = item.getCountryName();
        this.state = item.getState_2();
        this.postal_code= item.getPostalCode();
        this.suburb = item.getSuburb();
        this.district=item.getState_2();
    }
    public LatLng convertToLatLng(){
        LatLng latLng = new LatLng(getLatitude(),getLongitude());
        return latLng;
    }

    @Override
    public String toString() {
        if (urlParams == null) {
            return "";
        }
        return urlParams;
    }

    public String getCityStatePostCode(){
        return city+", "+state+", "+postal_code;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.latitude);
        dest.writeDouble(this.longitude);
        dest.writeString(this.city);
        dest.writeString(this.country);
        dest.writeString(this.streetName);
        dest.writeString(this.note);
        dest.writeString(this.device_token);
        dest.writeString(this.route);
        dest.writeString(this.suburb);
        dest.writeString(this.state);
        dest.writeString(this.urlParams);
        dest.writeString(this.postal_code);
        dest.writeString(this.streetNumber);
        dest.writeString(this.district);
        dest.writeFloat(this.rotation);
    }

    protected Address(Parcel in) {
        this.latitude = in.readDouble();
        this.longitude = in.readDouble();
        this.city = in.readString();
        this.country = in.readString();
        this.streetName = in.readString();
        this.note = in.readString();
        this.device_token = in.readString();
        this.route = in.readString();
        this.suburb = in.readString();
        this.state = in.readString();
        this.urlParams = in.readString();
        this.postal_code = in.readString();
        this.streetNumber = in.readString();
        this.district = in.readString();
        this.rotation= in.readFloat();
    }

    public static final Creator<Address> CREATOR = new Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel source) {
            return new Address(source);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };
}