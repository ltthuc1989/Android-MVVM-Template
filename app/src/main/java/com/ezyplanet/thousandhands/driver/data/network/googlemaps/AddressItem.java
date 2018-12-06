package com.ezyplanet.thousandhands.driver.data.network.googlemaps;


import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.ezyplanet.thousandhands.driver.data.network.request.Address;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Miso Tomovski - Venikom
 */
public class AddressItem implements Parcelable {

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
            if (addressComponent.getTypes().contains("street_number")||addressComponent.getTypes().contains("premise")) {
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
        String city=null ;
            for (AddressComponent addressComponent : address_components) {
                if (addressComponent.getTypes().contains("locality")) {
                    city= addressComponent.getLong_name();
                    return city;
                }
            }
        if(city==null){
                city=getState();
        }

        return city;
    }
    public String getSuburb(){
        suburb="";







            if(getCity()!=null){
                if(getNeighborhood()!=null){
                    suburb=getNeighborhood()+", "+getCity();
                }else {
                    if(getState()!=null&&!getState().equalsIgnoreCase(getCity())) {
                        suburb = getCity() + ", " + getState();
                    }else {
                        suburb =getCity();
                    }
                }

            }else {
                suburb = getState();
            }
            if(getPostalCode()!=null){
                suburb=suburb+", "+getPostalCode();
            }



        return suburb;
    }
    String getNeighborhood(){

            for (AddressComponent addressComponent : address_components) {
                if (addressComponent.getTypes().contains("neighborhood")) {
                    return addressComponent.getLong_name();
                }
            }
            return null;

    }

    public String getCountryName() {
        for (AddressComponent addressComponent : address_components) {
            if (addressComponent.getTypes().contains("country")) {
                return addressComponent.getLong_name();
            }
        }
        return null;
    }
    public String getRoute(){
        for (AddressComponent addressComponent : address_components) {
            if (addressComponent.getTypes().contains("route")) {
                return addressComponent.getLong_name();
            }
        }
        return null;
    }

    public String getState() {
        for (AddressComponent addressComponent : address_components) {
            if (addressComponent.getTypes().contains("administrative_area_level_1")) {
                return addressComponent.getShort_name();
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
    public String getState_3() {
        for (AddressComponent addressComponent : address_components) {
            if (addressComponent.getTypes().contains("administrative_area_level_3")||addressComponent.getTypes().contains("sublocality_level_1")) {

                      return addressComponent.getLong_name();

            }
        }
        return null;
    }
    String getSubLocality(){
        for (AddressComponent addressComponent : address_components) {
            if (addressComponent.getTypes().contains("administrative_area_level_3")||addressComponent.getTypes().contains("sublocality_level_1")) {
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
    public String getPostalCode() {
        for (AddressComponent addressComponent : address_components) {
            if (addressComponent.getTypes().contains("postal_code")) {
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

    public AddressItem() {
    }

    protected AddressItem(Parcel in) {
        this.address_components = new ArrayList<AddressComponent>();
        in.readList(this.address_components, AddressComponent.class.getClassLoader());
        this.formatted_address = in.readString();
        this.partial_match = in.readByte() != 0;
        this.geometry = in.readParcelable(Geometry.class.getClassLoader());
        this.types = in.createStringArrayList();
    }

    public static final Creator<AddressItem> CREATOR = new Creator<AddressItem>() {
        @Override
        public AddressItem createFromParcel(Parcel source) {
            return new AddressItem(source);
        }

        @Override
        public AddressItem[] newArray(int size) {
            return new AddressItem[size];
        }
    };

   public Address convertToLocation(String formatAdrress, String buildingInfo){
        Address address = new Address();
        if(getCountryName()!=null) {
            address.setCountry(getCountryName());
        }else{
//            if(UserManager.getInstance().getCountry()!=null){
//                address.setCountry(UserManager.getInstance().getCountry().getCountry());
//            }else{
//                if(UserManager.getInstance().getUser()!=null&&UserManager.getInstance().getUser().getLocation()!=null){
//                    address.setCountry(UserManager.getInstance().getUser().getLocation().getCountry());
//                }else {
//                    address.setCountry("Unknown");
//                }
//            }
        }
        if(buildingInfo!=null){
            address.setNote(buildingInfo);
        }
        address.setState(getState());
        address.setStreetName(formatAdrress);
        address.setLongitude(getGeometry().getLocation().getLng());
        address.setLatitude(getGeometry().getLocation().getLat());
        address.setSuburb(getSuburb());
        address.setCity(getCity());
        return address;

    }
    public String getShortAddress(){
       String streetNumber=getStreetNumber();
       String route=getRoute();
       String state3=getState_3();
        if(streetNumber!=null){
            String s=streetNumber;
            if(route!=null){
                s=s+" "+route;
                if(state3!=null){
                    s=s+", "+state3;
                }
                return s;
            }
            return s;


       }
       return null;
    }
}
