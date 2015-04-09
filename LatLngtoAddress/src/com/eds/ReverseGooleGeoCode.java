/**
 * This project is part of identifying Locations for Pickup and dropoff locations from latlng in the NYC Taxi data.
 * 
 * 
 * Use Google's GeoCode Api to Autocomplete a partital address provided 
 * Google's Geocode library
 * https://developers.google.com/maps/documentation/geocoding/    
 * use the JSON output returned by the library to retrieve the information 
 * Example 
 * http://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway
 * Input Partial Address
 * OutPut List of possible Address Match
 * 
 */
 
package com.eds;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author D.V.Santhosh
 *
 */
public class ReverseGooleGeoCode {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws JSONException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, JSONException, InterruptedException {
		
		//API Specs
		String URLString = "http://maps.googleapis.com/maps/api/geocode/json?"; //use "address=" or latlng=<lat>,<lang>;
		
		// Input file
		File inputFile = new File("C:\\Users\\D.V.Santhosh\\Desktop\\EDS\\First2500LatLng.csv");
		
		//Results file
		String absolutePath = inputFile.getAbsolutePath();
		String path = absolutePath.substring(0,absolutePath.lastIndexOf(File.separatorChar));
		String outputFile = path+"\\Results.csv";
		BufferedWriter writer = null;
		try{
			writer = new BufferedWriter(new FileWriter(new File(outputFile)));
		}catch (Exception e){
			e.printStackTrace();
			}
		
		//Read input file

		BufferedReader bufReader = new BufferedReader(new FileReader(inputFile));
		String line= bufReader.readLine();				// first line has got column header
		while((line = bufReader.readLine()) != null)
		{
			String [] lnglat = line.split(",");
			String latlng = "latlng="+lnglat[1]+","+lnglat[0]; 				//"+"&result_type=street_address&key=API_KEY" can be used for restricted result but needs https with API_KEY;
			Thread.sleep(200);
			String results = fetchSuggestions(URLString, latlng);

		
			// parse the Result String to JSON
			JSONObject myJSONResult = new JSONObject(results);
			String address = null;
			try{address = (String) ((JSONObject) ((JSONArray) myJSONResult.get("results")).get(0)).get("formatted_address");}catch(Exception e){}


			
			
			// Writing output to a file result
			try {	
				System.out.println(line +"," + address);
			    writer.write(line+","+address+"\n");

			} catch (IOException ex) {}
		}
		bufReader.close();
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
	
}
