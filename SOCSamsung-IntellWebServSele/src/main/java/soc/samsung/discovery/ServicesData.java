package soc.samsung.discovery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import soc.samsung.context.UserContext;

public class ServicesData {
	public void getServiceData(UserContext context){ //Input should be Service ID and user Context
		
		//		Assuming we get the URL details from service ID
		String url = "https://maps.googleapis.com/maps/api/directions/json?"
				+ "origin=" + context.getCurrentLocation() + "&destination=" + context.getToLocation() + "&"
				+ "key=" + context.apiKey;
		
		try {
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");

			int responseCode = con.getResponseCode();
//			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in;
			in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			
			//Send result for parsing
			parseJson(response.toString());
			
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
	
	/**
	 * 
	 * @param jsonString
	 */
	public void parseJson(String jsonString){
		
		JsonElement jelement = new JsonParser().parse(jsonString);
	    JsonObject  jobject = jelement.getAsJsonObject();
	    
//	    Get the first route
	    JsonArray routes = jobject.getAsJsonArray("routes");
	    JsonObject route = routes.get(0).getAsJsonObject();
	    
//	    Get the main leg of route from start to finish
	    JsonArray legs =route.getAsJsonArray("legs");
	    JsonObject leg = legs.get(0).getAsJsonObject();
	    
	    JsonObject objDuration = leg.getAsJsonObject("duration");
	    int duration = objDuration.get("value").getAsInt();
	    String durationTxt = objDuration.get("text").toString();

		System.out.println(duration + " Seconds\n" + durationTxt);
		
	}
}
