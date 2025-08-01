package twarita.restAssured.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import twarita.restAssured.files.MockedResponses;
import twarita.restAssured.files.ReusableFiles;

public class sumValidationWithTestNG {

	@Test
	public void sumValidation() {
		// Verify if Sum of all Course prices matches with Purchase Amount
		JsonPath response = ReusableFiles.rawToJSON(MockedResponses.mockedComplexJSONResponse());

		int noOfCourses = response.getInt("courses.size()");
		int purchaseAmount = response.getInt("dashboard.purchaseAmount");

		int price = 0;

		for (int i = 0; i < noOfCourses; i++) {
			price = price + (Integer.parseInt(response.getString("courses[" + i + "].price"))
					* response.getInt("courses[" + i + "].copies"));
		}

		Assert.assertEquals(price, purchaseAmount);

	}

}