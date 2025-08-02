package twarita.restAssured.tests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import twarita.restAssured.files.Payload;
import twarita.restAssured.files.ReusableFiles;

public class DataParameterization {
	
	
	
	
	@Test
	public void addBook() {
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		String addBokoResponse = given().header("Content-Type","application/json").body(Payload.addBook("gjsskh", "jsgdjka"))
		.when().post("/Library/Addbook.php").
		then().extract().response().asString();
		
		JsonPath js =ReusableFiles.rawToJSON(addBokoResponse);
		System.out.println(js.getString("ID"));
	}

}
