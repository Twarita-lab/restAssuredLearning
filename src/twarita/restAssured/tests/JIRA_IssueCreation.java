package twarita.restAssured.tests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import twarita.restAssured.files.ReusableFiles;

public class JIRA_IssueCreation {

	@Test(enabled = true)
	public void createBug() {
		RestAssured.baseURI ="https://twaritachoudhury.atlassian.net";
		
		String createBugResponse = 
				given().header("Content-Type","application/json").
				header("Authorization","Basic dHdhcml0YS5jaG91ZGh1cnk5M0BnbWFpbC5jb206QVRBVFQzeEZmR0YwYUVGLVZqcWdCMTV3QkJSRGo5TFpwSmlhSkFwUW14bUp4dWdKNHZTUEZwUThCaFdrS0lwdnFKeVhvcjlrQjNUY3dtNEVTWDlfTU1kMVYxb0tPaW5md3NnSERJZlJOaXNSRUxBTTRsMHJzOFE0ZTJOWmZJY2R0VTRRaF9YZ0NzeWdaNFAtUm5xUEpTODVKY0V6T2czVTc1czBFU3NOY2xUaW9IRGtzS2lKaXlVPTlDOTkxMjcy")
				.body("{\r\n"
						+ "    \"fields\":{\r\n"
						+ "        \"project\":{\r\n"
						+ "            \"key\":\"SCRUM\"\r\n"
						+ "        },\r\n"
						+ "        \"summary\":\"first test issue via automation\",\r\n"
						+ "        \"issuetype\":{\r\n"
						+ "            \"name\":\"Bug\"\r\n"
						+ "        }\r\n"
						+ "    }\r\n"
						+ "\r\n"
						+ "}")
				.when().post("/rest/api/3/issue")
				.then().log().all().assertThat().statusCode(201).extract().asString();
		
		JsonPath createBugResponseJSON = ReusableFiles.rawToJSON(createBugResponse);
		String key = createBugResponseJSON.getString("key");
		System.out.println(key);
	}
	
	/*
	 * @Test(enabled = false) public void attachScreenshotToTheBug() {
	 * RestAssured.baseURI ="https://twaritachoudhury.atlassian.net";
	 * 
	 * String createBugResponse = given().contentType(ContentType.JSON).
	 * header("Authorization",
	 * "dHdhcml0YS5jaG91ZGh1cnk5M0BnbWFpbC5jb206QVRBVFQzeEZmR0YwYUVGLVZqcWdCMTV3QkJSRGo5TFpwSmlhSkFwUW14bUp4dWdKNHZTUEZwUThCaFdrS0lwdnFKeVhvcjlrQjNUY3dtNEVTWDlfTU1kMVYxb0tPaW5md3NnSERJZlJOaXNSRUxBTTRsMHJzOFE0ZTJOWmZJY2R0VTRRaF9YZ0NzeWdaNFAtUm5xUEpTODVKY0V6T2czVTc1czBFU3NOY2xUaW9IRGtzS2lKaXlVPTlDOTkxMjcy")
	 * .pathParam("Key", new JIRA_IssueCreation().createBug()) .body("{\r\n" +
	 * "    \"fields\":{\r\n" + "        \"project\":{\r\n" +
	 * "            \"key\":\"SCRUM\"\r\n" + "        },\r\n" +
	 * "        \"summary\":\"first test issue via automation\",\r\n" +
	 * "        \"issuetype\":{\r\n" + "            \"name\":\"Bug\"\r\n" +
	 * "        }\r\n" + "    }\r\n" + "\r\n" + "}")
	 * .when().post("/rest/api/3/issue/:Key/attachments")
	 * .then().log().all().assertThat().statusCode(201).extract().asString();
	 * 
	 * JsonPath createBugResponseJSON = ReusableFiles.rawToJSON(createBugResponse);
	 * 
	 * }
	 */

}
