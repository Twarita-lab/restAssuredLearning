package twarita.restAssured.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import twarita.restAssured.files.GooglePlaces;
import twarita.restAssured.files.ReusableFiles;

public class AddUpdateVerifyNewAddress {

	public static void main(String[] args) {

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String keyValue = "qaclick123";
		String newAddress = "80 winter walk, USA";

		// Add place
		String addPlaceResponseString = given().queryParam("key", keyValue).body(GooglePlaces.addGooglePlacesBody())
				.when().post("/maps/api/place/add/json").then().assertThat().statusCode(200).extract().response()
				.asPrettyString();

		// Converting Response to JSON
		JsonPath addPlaceResponseJSON = new JsonPath(addPlaceResponseString);
		String placeIdValue = addPlaceResponseJSON.getString("place_id");

		// Update Address

		String updateAddressResponseString = given().queryParam("place_id", placeIdValue).queryParam("key", keyValue)
				.body("{\r\n" + "\"place_id\":\"" + placeIdValue + "\",\r\n" + "\"address\":\"" + newAddress + "\",\r\n"
						+ "\"key\":\"" + keyValue + "\"\r\n" + "}")
				.when().put("maps/api/place/update/json").then().extract().asString();

		//  Converting Response to JSON using custom build Reusable file to implement Abstraction
		JsonPath updateAddressResponseJSON =ReusableFiles.rawToJSON(updateAddressResponseString);

		//TestNG Assertion to have proper reports
		Assert.assertEquals(updateAddressResponseJSON.getString("msg"), "Address successfully updated");

		// Verify address updated successfully
		//equalTo is coming from static hamcrest Matchers, which needs manual import
		given().queryParam("place_id", placeIdValue).queryParam("key", keyValue).when().get("/maps/api/place/get/json")
				.then().body("address", equalTo(newAddress));

	}

}
