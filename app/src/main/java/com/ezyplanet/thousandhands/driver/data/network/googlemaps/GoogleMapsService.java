package com.ezyplanet.thousandhands.driver.data.network.googlemaps;


import android.os.AsyncTask;
import android.util.Log;

import com.ezyplanet.thousandhands.driver.BuildConfig;
import com.ezyplanet.thousandhands.driver.ui.listener.IAsyncCallback;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 
 * @author Miso Tomovski - Venikom
 *
 */
public class GoogleMapsService extends AsyncTask<String, Void, String> {

	private IAsyncCallback<String> callback;
	private String input;


	public GoogleMapsService(IAsyncCallback<String> callback, String input){
		this.callback = callback;
		this.input = input;

	}



	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		callback.handlePreExecute();
	}
	
	@Override
	protected String doInBackground(String... params) {
		return mapsApi(params[0]);
	}
	
	private String mapsApi(String apiUrl){
		HttpURLConnection conn = null;
	    StringBuilder jsonResults = new StringBuilder();
	    try {
			URL url;
	        if(input!=null ){


				if(input.contains("http")){
					url = new URL(input);


				}else{
					//url = new URL(apiUrl+urlEncode(input));

					url = new URL(apiUrl+urlEncode(input)+"&key="+ BuildConfig.MAP_GEO_CODING_API_KEY);

				}

			}else{
				return null;
			}


	        conn = (HttpURLConnection) url.openConnection();
	        InputStreamReader in = new InputStreamReader(conn.getInputStream());

	        // Load the results into a StringBuilder
	        int read;
	        char[] buff = new char[1024];
	        while ((read = in.read(buff)) != -1) {
	            jsonResults.append(buff, 0, read);
	        }
	    } catch (MalformedURLException e) {
	        Log.e("Autocomplete", "Error processing MAPS API URL", e);
	        return null;
	    } catch (IOException e) {
	        Log.e("Autocomplete", "Error connecting to MAPS API", e);
	        return null;
	    } finally {
	        if (conn != null) {
	            conn.disconnect();
	        }
	    }


	    return jsonResults.toString();
	    
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
       try {
		   callback.handlePostExecute(result);
	   }catch (IllegalStateException error){

	   }

	}
	static String urlEncode(String value) {
		try {
			return URLEncoder.encode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return value;
		}
	}
}
