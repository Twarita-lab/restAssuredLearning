package twarita.restAssured.tests;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		//RestAssured used given(), when(), then() method; which are part of RestAssured's static library
		// Hence, we need to manually add that using static keyword
		
		//log().all() can be used just after given() to log the request
		//and after then() to log the response
		
		
		//given() takes all details that we need to send for that API, except method and resource
		RequestSpecification getRequestsDetails = given().log().all().queryParam("place_id", "b053b4da3d21a4d2ed74226d575f5261");
		
		//when() takes method and resource info
		 Response getRequestsMethod = getRequestsDetails.when().get("maps/api/place/get/json");
		 
		 //validate status code
		 //assertThat() is not mandatory, but servs assertion purppose
		 //return expectation error if fails
		 ValidatableResponse statusCodeValidation = getRequestsMethod.then().log().all().assertThat().statusCode(200);
		 
		 ValidatableResponse responseBodyValidation = getRequestsMethod.then().statusCode(200);
		 
		 

	}

}
