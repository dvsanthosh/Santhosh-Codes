import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Text;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

	/**
	 * @author D.V.Santhosh
	 *
	 */
	public class Convert {

		/**
		 * @param args
		 * @throws IOException 
		 * @throws JSONException 
		 * @throws InterruptedException 
		 */

		
		boolean batchConvert(String inFile, String outFile, Text consoleOutput, ProgressBar progressBar) throws IOException, JSONException, InterruptedException {
			
			//API Specs
			String URLString = "http://maps.googleapis.com/maps/api/geocode/json?"; //use "address=" or latlng=<lat>,<lang>;
			
			// Input & output files
			// assert File inputFile = new File("C:\\Users\\D.V.Santhosh\\Desktop\\EDS\\First2500LatLng.csv");
			File inputFile = new File (inFile);
			File outputFile = new File(outFile);
			
			//Results file
			BufferedWriter writer = null;
			consoleOutput.append("In Convert Results written to: "+ outFile+"\n");
			try{
				writer = new BufferedWriter(new FileWriter(outputFile));
			}catch (Exception e){
				e.printStackTrace();
				}
			
			//Read input file
			consoleOutput.append(" In Convert Reading from: "+ inFile+"\n");
			BufferedReader bufReader = new BufferedReader(new FileReader(inputFile));
			String line= bufReader.readLine();				// first line has got column header
			while((line = bufReader.readLine()) != null)
			{
				int progress=1;
				String [] lnglat = line.split(",");
				String latlng = "latlng="+lnglat[1]+","+lnglat[0]; 				//"+"&result_type=street_address&key=API_KEY" can be used for restricted result but needs https with API_KEY;
				Thread.sleep(200);
				
				String results = fetchSuggestions(URLString, latlng);

				// parse the Result String to JSON
				JSONObject myJSONResult = new JSONObject(results);
				String address = null;
				try{address = (String) ((JSONObject) ((JSONArray) myJSONResult.get("results")).get(0)).get("formatted_address");
				}catch(Exception e){
					e.printStackTrace();
					continue;
					}

				// Writing output to a file result
				try {	
				    writer.write(line+","+address+"\n");
					consoleOutput.append(progress+". "+line +"," + address+"\n");
					progressBar.setSelection(progress*progressBar.getMaximum()/2500);
				} catch (IOException ex) {}
			}
			bufReader.close();
			writer.close();
			consoleOutput.append("Completed!!!"+"\n");
			progressBar.setSelection(progressBar.getMaximum());
			return true;
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
		
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			return replyString;
		}
	}

