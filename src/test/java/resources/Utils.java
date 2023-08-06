package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification req;
	
	public RequestSpecification requestSpecification() throws IOException {
		
		if(req==null) {
		
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		
		 req = new RequestSpecBuilder()
				.setBaseUri(getGlobalValue("baseUrl"))
				.addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
		
		
		return req;
	}
	return req;

}
	
	
	public static String getGlobalValue(String key) throws IOException {
		
		Properties prop = new Properties();
		
		FileInputStream fis = new FileInputStream("D:\\Eclipse_Workspace\\APIFramework\\src\\test\\java\\resources\\global.properties");
		
		prop.load(fis);
		prop.getProperty(key);
		return prop.getProperty(key);
		
	}
	
	
	public String stringresponseAssertionusingObjectMapper(String response, String key)
			throws JsonMappingException, JsonProcessingException {

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = objectMapper.readTree(response);
		String value = rootNode.get(key).asText();
		return value;
	}

	
	public String getJsonpath(Response response, String key) {
	    JsonPath jsonPath = response.jsonPath();
	    return jsonPath.getString(key);
	}


	public String getJsonpath2(Response response, String key) {
		io.restassured.path.json.JsonPath jsonPath = response.jsonPath();
		String value = jsonPath.getString(key);

		return value;
	}
	


}
