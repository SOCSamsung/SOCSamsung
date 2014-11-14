package soc.samsung.context;

public class UserContext {
	private String currLocation;
	private String modeOfTransport;
	private String toLocation;
	
//	This is the google API Key
	public String apiKey;
	
	public UserContext(){
		apiKey = "AIzaSyDVWOo1J7jswc1NKmrN93beWMpobhlzOEE";
		currLocation = "Mountainview";
		toLocation = "Sanjose"; 
	}
	
	public String getCurrentLocation(){
		return currLocation;
	}
	
	public String getModeofTravel(){
		return modeOfTransport;
	}
	
	public String getToLocation(){
		return toLocation;
	}
}
