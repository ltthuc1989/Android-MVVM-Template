package com.ezyplanet.thousandhands.driver.ui.listener;

/**
 * 
 * @author Miso Tomovski - Venikom
 *
 * @param <T>
 */
public interface IAsyncCallback<T> {
	void handlePreExecute();
	//public T handleBackground(T params);
	void handlePostExecute(T result);
}
