package com.ezyplanet.thousandhands.driver.data.network.googlemaps;

import java.util.List;

/**
 * 
 * @author Miso Tomovski - Venikom
 *
 */
public class ResultWrapper {
	
	private List<AddressItem> results;
	private String status;
	
	public List<AddressItem> getResults() {
		return results;
	}
	public void setResults(List<AddressItem> results) {
		this.results = results;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
