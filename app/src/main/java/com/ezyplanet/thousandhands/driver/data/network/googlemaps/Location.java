package com.ezyplanet.thousandhands.driver.data.network.googlemaps;

import android.os.Parcel;
import android.os.Parcelable;

public class Location implements Parcelable{
	
	private double lat;
	private double lng;

	private String city;
	
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeDouble(this.lat);
		dest.writeDouble(this.lng);
		dest.writeString(this.city);
	}

	public Location() {
	}

	protected Location(Parcel in) {
		this.lat = in.readDouble();
		this.lng = in.readDouble();
		this.city = in.readString();
	}

	public static final Creator<Location> CREATOR = new Creator<Location>() {
		public Location createFromParcel(Parcel source) {
			return new Location(source);
		}

		public Location[] newArray(int size) {
			return new Location[size];
		}
	};
}
