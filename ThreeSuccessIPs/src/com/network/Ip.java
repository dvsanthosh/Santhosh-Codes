package com.network;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Ip {

	public static void main(String[] args) throws IOException {
		// Set the file name and Prints the success ips
		
		String inputFile = "input000.txt";
		String test = null;
		
		//Read the file
		try {
			test = readFile(inputFile);
		} catch (Exception e) {
			// File Read Exception
			e.printStackTrace();
		}

		
		      
		String result = getSuccessIps(test);
		
		System.out.println(result);
		

	}
	
	public static String getSuccessIps(String test){
		ArrayList<String> check3Ips = new ArrayList<String>();	// temp storing success ips
		ArrayList<String> successIps = new ArrayList<String>();	// list of Ips already validated. finally returned.
		
		String ip = "(\\d{1,3}\\.){3}\\d{1,3}";
        String time = "time=\\d+";
        
        Pattern ipPatt = Pattern.compile(ip);
        Pattern timePatt = Pattern.compile(time);
        Matcher matcherIp = ipPatt.matcher(test);
        Matcher matcherTime= timePatt.matcher(test);
        
        
        while(matcherTime.find() && matcherIp.find()){
        	if (Float.parseFloat(matcherTime.group().substring(5)) < 5.0f){
        		 check3Ips.add(matcherIp.group());
        		try{
        			if (!check3Ips.get(0).equals(check3Ips.get(1)))
        				check3Ips.remove(0);
        		}catch (Exception e){}
        		
        		try{
        			if (!check3Ips.get(1).equals(check3Ips.get(2))){
        				check3Ips.remove(0);
        				check3Ips.remove(1);
        		   	}
        			else if (check3Ips.get(0).equals(check3Ips.get(1)) && check3Ips.get(1).equals(check3Ips.get(2))){
        				successIps.add(check3Ips.get(0));
        			}
        			      			
        		}catch (Exception e){}
        	}

        }
        
        return successIps.toString();
        //System.out.println("Success Ips List is: " + successIps.toString());
	}




public static String readFile(String fileName) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(fileName));
    try {
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();

        while (line != null) {
            sb.append(line);
            sb.append("\n");
            line = br.readLine();
        }
        return sb.toString();
    } finally {
        br.close();
    }
}

}
