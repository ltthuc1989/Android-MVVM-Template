package com.ezyplanet.thousandhands.driver.data.network.googlemaps;

/**
 * 
 * @author Miso Tomovski - Venikom
 *
 */
public class PlaceWrapper {
	
	AddressItem result;
	private String status;
	


	public AddressItem getResult() {
		return result;
	}


	public void setResult(AddressItem  result) {
		this.result = result;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
