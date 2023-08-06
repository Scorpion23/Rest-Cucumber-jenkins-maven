package stepDefinition;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils {
	
	RequestSpecification reqspec;
	 ResponseSpecification resspec;
	 Response response;
	static String Oplaceid;
	
	
	
	TestDataBuild data= new TestDataBuild();
	
	
	
	
	
	@Given("Add place payload with {string}{string}{string}")
	public void add_place_payload_with(String string, String string2, String string3)  throws IOException {
		
		 
		 reqspec = given().spec(requestSpecification()).body(data.addPlacePayLoad(string,string2,string3));
		 System.out.println("AddPlaceAPI started");
	 
	}
	@When("User calls {string} with {string} http request")// sets response
	public void user_calls_with_http_request(String string,String method) {
		
		APIResources resourceAPI = APIResources.valueOf(string);
		

		
		 //resspec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
		//response = reqspec.when().post(resourceAPI.getResource()).then().spec(resspec).extract().response();
		
		
		    resspec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();

		    if (method.equalsIgnoreCase("POST")) {
		        response = reqspec.when().post(resourceAPI.getResource()).then().spec(resspec).extract().response();
		    } else if (method.equalsIgnoreCase("DELETE")) {
		        response = reqspec.when().delete(resourceAPI.getResource()).then().spec(resspec).extract().response();
		    } else if (method.equalsIgnoreCase("GET")) {
		        response = reqspec.when().get(resourceAPI.getResource()).then().spec(resspec).extract().response();
		    }else if(method.equalsIgnoreCase("PUT")) {
		    	
		    	response = reqspec.when().put(resourceAPI.getResource()).then().spec(resspec).extract().response();
		    }
		


	   
	}
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(int x) {
		
		assertEquals(response.getStatusCode(), 200);
		//resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	    
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String string, String string2) throws JsonMappingException, JsonProcessingException {
		String re= response.asString();
		
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = objectMapper.readTree(re);
		 Oplaceid = rootNode.get("place_id").asText();
		String Oscope = rootNode.get("scope").asText();
		
        System.out.println("ObjectMapper Place ID: " + Oplaceid);
        System.out.println("ObjectMapper Scope: " + Oscope);
		
		
		
		io.restassured.path.json.JsonPath jsonPath = response.jsonPath();
		 String placeId = jsonPath.getString("place_id");
	        String scope1 = jsonPath.getString("scope");
	        System.out.println("jsonPath Place ID: " + placeId);
	        System.out.println("jsonPath Scope: " + scope1);

	}
	
	@Then("check placeid maps to the {string} using {string}")
	public void check_maps_to_the_using(String expectedName, String resource) throws IOException {
		
		
	    
		/*
		  APIResources resourceAPI = APIResources.valueOf(resource);
		Response resp3 = given().spec(requestSpecification()).queryParam("place-id",Oplaceid).when().get(resourceAPI.getResource()).then().extract().response();
		
		
		ObjectMapper objectMapper2 = new ObjectMapper();
		JsonNode rootNode2 = objectMapper2.readTree(reqspec3);
		String Actualname = rootNode2.get("name").asText();
		Assert.assertEquals(Actualname, string2);
		System.out.println("Test passed");
	    */
		
		String placeid = getJsonpath(response, "place_id");
		reqspec = given().spec(requestSpecification()).queryParams("place_id", placeid);

		user_calls_with_http_request(resource, "GET");
		String placename = getJsonpath(response, "name");

		Assert.assertEquals(placename, expectedName);
		System.out.println("Assertion passed for getplaceapi");
		
	}
	
	

	@Given("Put place payload with place-id,key and {string}")
	public void put_place_payload_with_place_id(String string) throws IOException {
		String key = "qaclick123";

		reqspec = given().spec(requestSpecification()).body(data.putPlacePayLoad2(Oplaceid, key, string));

	}

	@Then("check if {string} is {string}")
	public void check_if_are_correctly_updated(String key, String expectedmessage) {

		String actualmessage = getJsonpath(response, key);
		Assert.assertEquals(actualmessage, expectedmessage);

		System.out.println("Assertion passed for putplaceapi");

	}
	

	@Given("DeletePlace payload")
	public void delete_place_payload() throws IOException {
		
	    
		reqspec=given().spec(requestSpecification()).body(data.deletePlacepayload(Oplaceid));
		System.out.println("Assertion passed for deleteplaceapi");
	}
	
	@Then("{string} in the response body is {string}")
	public void in_the_response_body_is(String string, String expectedDelmessage) throws JsonMappingException, JsonProcessingException {
		
		String Actualdeleteresp = getJsonpath(response,string);
		
		Assert.assertEquals(Actualdeleteresp, expectedDelmessage);
		
	}

	@DataProvider(name = "BooksData")

	public Object[][] getData() {
		// Multi dimensional Array
		return new Object[][] { { "fdsfd", "488" }, { "dfdsf", "545" }, { "dfdsf", "4562" } };

	}
	
	
	
}
