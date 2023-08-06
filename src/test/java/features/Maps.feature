Feature: Validating Place API's

@smoke @AddPlace @all
Scenario Outline: verify if place is being Successfully added using AddPlaceAPI
    Given Add place payload with "<name>""<language>""<address>"
    When User calls "AddPlaceAPI" with "POST" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
    And "Scope" in response body is "APP"
    And check placeid maps to the "<name>" using "getPlaceAPI"
    And Verify the time

Examples:

|name						|language	|address	|
|Sachin House		|Telugu		|KPHB			|


@smoke @PutPlace @regression @all
Scenario Outline: verify if place details are updated Successfully  using PutPlaceAPI

Given Put place payload with place-id,key and "address"
When User calls "putPlaceAPI" with "PUT" http request
Then the API call got success with status code 200
And check if "msg" is "Address successfully updated"
 And Verify the time
Examples:

|address|
|Mayuri Mansion,Kukatpally,500085|


@DeletePlace @regression @all
Scenario Outline: verify if Delete Place functionality is working

Given DeletePlace payload
When User calls "deletePlaceAPI" with "DELETE" http request
Then the API call got success with status code 200
And "status" in the response body is "OK"
 And Verify the time