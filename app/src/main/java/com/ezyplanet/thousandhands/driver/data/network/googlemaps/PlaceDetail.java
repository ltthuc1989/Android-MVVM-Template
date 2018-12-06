package com.ezyplanet.thousandhands.driver.data.network.googlemaps;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PV on 3/2/2017.
 */

public class PlaceDetail implements Parcelable{
    private List<AddressComponent> address_components;
    private String formatted_address;
    private boolean partial_match;
    private Geometry geometry;
    private List<String> types;
    String suburb;

    public List<AddressComponent> getAddress_components() {
        return address_components;
    }

    public void setAddress_components(List<AddressComponent> address_components) {
        this.address_components = address_components;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public boolean isPartial_match() {
        return partial_match;
    }

    public void setPartial_match(boolean partial_match) {
        this.partial_match = partial_match;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public String getStreetNumber() {

        for (AddressComponent addressComponent : address_components) {
            if (addressComponent.getTypes().contains("street_number")) {
                return addressComponent.getLong_name();
            }
        }
        return null;
    }

    public String getAddress() {
        String address = "";
        for (AddressComponent addressComponent : address_components) {
            if (addressComponent.getTypes().contains("establishment")) {
                address = addressComponent.getLong_name();
                return address;
            }

            if (addressComponent.getTypes().contains("route")) {
                address += " " + addressComponent.getLong_name();
                return address;
            }
        }
        return null;
    }

    public String getCity() {
        for (AddressComponent addressComponent : address_components) {
            if (addressComponent.getTypes().contains("locality")) {
                return addressComponent.getLong_name();
            }
        }
        return null;
    }
    public String getSuburb(){
        suburb="";
        if(this.getState()!=null&&!this.getState().isEmpty()){
            suburb = this.getState();
        }else{
            if(getCity()!=null&&!getCity().isEmpty()){
                suburb = getCity();
            }else{
                suburb = "Unknown";
            }
        }
        if(getState_2()!=null&&!getState_2().isEmpty()){
            suburb = getState_2()+", "+suburb;
        }

        return suburb;
    }

    public String getCountryName() {
        for (AddressComponent addressComponent : address_components) {
            if (addressComponent.getTypes().contains("country")) {
                return addressComponent.getLong_name();
            }
        }
        return null;
    }

    public String getState() {
        for (AddressComponent addressComponent : address_components) {
            if (addressComponent.getTypes().contains("administrative_area_level_1")) {
                return addressComponent.getLong_name();
            }
        }
        return null;
    }
    public String getState_2() {
        for (AddressComponent addressComponent : address_components) {
            if (addressComponent.getTypes().contains("administrative_area_level_2")) {
                return addressComponent.getLong_name();
            }
        }
        return null;
    }

    public String getCountryCode() {
        for (AddressComponent addressComponent : address_components) {
            if (addressComponent.getTypes().contains("country")) {
                return addressComponent.getShort_name();
            }
        }
        return null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.address_components);
        dest.writeString(this.formatted_address);
        dest.writeByte(this.partial_match ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.geometry, flags);
        dest.writeStringList(this.types);
    }

    public PlaceDetail() {
    }

    protected PlaceDetail(Parcel in) {
        this.address_components = new ArrayList<AddressComponent>();
        in.readList(this.address_components, AddressComponent.class.getClassLoader());
        this.formatted_address = in.readString();
        this.partial_match = in.readByte() != 0;
        this.geometry = in.readParcelable(Geometry.class.getClassLoader());
        this.types = in.createStringArrayList();
    }

    public static final Creator<PlaceDetail> CREATOR = new Creator<PlaceDetail>() {
        @Override
        public PlaceDetail createFromParcel(Parcel source) {
            return new PlaceDetail(source);
        }

        @Override
        public PlaceDetail[] newArray(int size) {
            return new PlaceDetail[size];
        }
    };
}
