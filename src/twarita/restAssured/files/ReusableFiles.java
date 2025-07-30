package twarita.restAssured.files;

import io.restassured.path.json.JsonPath;

public class ReusableFiles {
	
	public static JsonPath rawToJSON(String response) {
		JsonPath js = new JsonPath(response);
		return js;
		
	}

}
