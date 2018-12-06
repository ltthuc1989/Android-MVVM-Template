package com.ezyplanet.thousandhands.driver.data.network.googlemaps.place;


import android.os.Parcel;
import android.os.Parcelable;

import static com.ezyplanet.thousandhands.driver.util.AppConstants.GOOGLE_API_KEY;
import static com.ezyplanet.thousandhands.driver.util.AppConstants.GOOGLE_MAPS_API__PLACE_DETAILS;


/**
 * Created by PV on 2/28/2017.
 */

public class PlaceAddress implements Parcelable {

    String note;
    String address;
    String lat;
    String lng;
    String icon;
    String place_id;
    String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return GOOGLE_MAPS_API__PLACE_DETAILS+"key="+ GOOGLE_API_KEY+"&place_id="+place_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.note);
        dest.writeString(this.address);
        dest.writeString(this.lat);
        dest.writeString(this.lng);
        dest.writeString(this.icon);
        dest.writeString(this.place_id);
        dest.writeString(this.name);
    }

    public PlaceAddress() {
    }

    protected PlaceAddress(Parcel in) {
        this.note = in.readString();
        this.address = in.readString();
        this.lat = in.readString();
        this.lng = in.readString();
        this.icon = in.readString();
        this.place_id = in.readString();
        this.name = in.readString();
    }

    public static final Creator<PlaceAddress> CREATOR = new Creator<PlaceAddress>() {
        @Override
        public PlaceAddress createFromParcel(Parcel source) {
            return new PlaceAddress(source);
        }

        @Override
        public PlaceAddress[] newArray(int size) {
            return new PlaceAddress[size];
        }
    };


}
