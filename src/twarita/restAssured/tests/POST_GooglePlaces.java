package twarita.restAssured.tests;


import twarita.restAssured.files.Payload;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;


public class POST_GooglePlaces {

	public static void main(String[] args) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		//GooglePlaces gPF = new GooglePlaces();
		
		Response postGoogleAddRequest = given().log().all().queryParam("key", "qaclick121").body(Payload.addGooglePlacesBody()).
				when().post("maps/api/place/add/json");

		//Validations
		postGoogleAddRequest.then().assertThat().body("status", equalTo("OK"));
		
		postGoogleAddRequest.then().assertThat().header("Server", "Apache/2.4.52 (Ubuntu)");
		
		
		//print response
		
		String response = postGoogleAddRequest.then().extract().asPrettyString();
		
		//parsing JSON
		JsonPath js = new JsonPath(response);
		System.out.println("Place id is "+js.getString("place_id"));
		
	}

}
