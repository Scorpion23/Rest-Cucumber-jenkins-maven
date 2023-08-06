package stepDefinition;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		
		StepDefinition hook = new StepDefinition();
		
		if(hook.Oplaceid==null) {
		hook.add_place_payload_with("Sachin House","Telugu","KPHB"); // creating RequestSpecification
		hook.user_calls_with_http_request("AddPlaceAPI","POST");//creating response
		hook.in_response_body_is("status","OK");//creating place_id for use in @DeletePlace
	}
	}
	
}
