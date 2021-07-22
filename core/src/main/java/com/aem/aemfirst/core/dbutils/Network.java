package com.aem.aemfirst.core.dbutils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public final class Network {
	
	private static final String USER_AGENT = "Mozilla/5.0";
	public static String readJson(String url) {
		
		try {
			
			URL requestURL = new URL(url);
			HttpsURLConnection connection 	= (HttpsURLConnection) requestURL.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("USER-AGENT", USER_AGENT);
			int responsecode = connection.getResponseCode();
			if (responsecode == HttpsURLConnection.HTTP_OK) {
				
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				
                 while ((inputLine = in.readLine()) != null) {
					
					response.append(inputLine);
				}
                 in.close();
                 return response.toString();
			} 			
			
		}catch (MalformedURLException e) {
			e.printStackTrace(); 
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";
		
		
	}

}
