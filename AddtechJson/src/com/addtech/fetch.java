/**
 * Use Google's GeoCode Api to Autocomplete a partital address provided 
 * Google's Geocode library
 * https://developers.google.com/maps/documentation/geocoding/    
 * use the JSON output returned by the library to retrieve the information 
 * Example 
 * http://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway
 * Input Partial Address
 * OutPut List of possible Address Match
 */
package com.addtech;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author D.V.Santhosh
 *
 */
public class fetch{

	/**
	 * Prints all the postal addresses for a user query 
	 * 
	 * 
	 */
	public static void main(String[] args) throws JSONException {
		
		String address = getUserInput();		//assert address = "1602+Amphitheatre+Parkway"
				
		String URLString = "http://maps.googleapis.com/maps/api/geocode/json?address=";
		
		String results = fetchSuggestions(URLString, address);		// This has fetched entire reply from Google GeoCode API

		
		// parse the Result String to JSON
        JSONObject myJSONResult = new JSONObject(results);
  	   	for (int i = 0; i <((JSONArray) myJSONResult.get("results")).length(); i++) 
		System.out.println(((JSONObject) ((JSONArray) myJSONResult.get("results")).get(i)).get("formatted_address"));
		
	}
	
	/**
	 * This Makes HTTP connection with Web Service and passes query parameters and returns search results
	 * 
	 * @param URLString URL of GeoCode API is passed as String with the query parameters
	 * @param address user query is passed as String.
	 * @return replyString reply from web service as a String
	 */

	private static String fetchSuggestions(String URLString,String address) throws JSONException {
		String line;
		String replyString = "";
		try {
			URL url = new URL(URLString+address); 		//assert URL url = new URL("http://maps.googleapis.com/maps/api/geocode/json?address=" + address);
			HttpURLConnection httpUrlConn = (HttpURLConnection)url.openConnection();
			BufferedReader replyBuffReader = new BufferedReader(new InputStreamReader(httpUrlConn.getInputStream()));
		    
			while ((line = replyBuffReader.readLine()) != null) {
				replyString = replyString.concat(line);
				}
		        
			replyBuffReader.close();
		    httpUrlConn.disconnect();   
	
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return replyString;
	}
	
	/**
	 * This gets address as user Input and formats as query tokens
	 * 
	 */

	private static String getUserInput() {
		System.out.println(" Enter few chars from Address");
		Scanner in = new Scanner(System.in);
		String address = in.nextLine().replace(" ", "+");
		in.close();
		return address;
	}

}
