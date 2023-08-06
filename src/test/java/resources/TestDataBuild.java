package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	
	
	
	public AddPlace addPlacePayLoad(String name,String language,String address) {
		
		
		Location loc = new Location();
		loc.setLat(15.821421009811315);
		loc.setLng(78.0284185278079);

		List<String> sendarrtypes = new ArrayList<>();
		sendarrtypes.add("Metro");
		sendarrtypes.add("JNTUH");

		// AddPlace addplacebody = new AddPlace(loc, 50, "My House", "(+91) 983 893
		// 3937", "29, kukatpally, Hyderabad",
		// sendarrtypes, "http://kp.com", "Telugu");

		AddPlace addplacebody = new AddPlace();
		addplacebody.setLocation(loc);
		addplacebody.setAccuracy(50);
		addplacebody.setName(name);
		addplacebody.setPhone_number("(+91) 983 893 3937");
		addplacebody.setAddress(address);
		addplacebody.setTypes(sendarrtypes);
		addplacebody.setWebsite("http://kp.com");
		addplacebody.setLanguage(language);
		
		
		return addplacebody;

	}
	
	
	public String putPlacePayLoad2(String placeid, String key, String address) {

	    String putbody = "{\r\n"
	            + "    \"place_id\": \"" + placeid + "\",\r\n"
	            + "    \"key\": \"" + key + "\",\r\n"
	            + "    \"address\": \"" + address + "\"\r\n"
	            + "}";
	    
	    return putbody;
	}
	
	
	
	public String deletePlacepayload(String placeid) {
		
		String deletebody="{\r\n"
				+ "    \"place_id\":\""+placeid+"\"\r\n"
				+ "}";
		return deletebody;
		
		
		
	}



}
