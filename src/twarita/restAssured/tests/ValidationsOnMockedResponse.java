package twarita.restAssured.tests;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;
import twarita.restAssured.files.MockedResponses;
import twarita.restAssured.files.ReusableFiles;

public class ValidationsOnMockedResponse {

	public static void main(String[] args) {
		/*
		 * 1. Print No of courses returned by API
		 * 
		 * 2.Print Purchase Amount
		 * 
		 * 3. Print Title of the first course
		 * 
		 * 4. Print All course titles and their respective Prices
		 * 
		 * 5. Print no of copies sold by RPA Course
		 * 
		 * 6. Verify if Sum of all Course prices matches with Purchase Amount
		 */
		
		JsonPath response = ReusableFiles.rawToJSON(MockedResponses.mockedComplexJSONResponse());
		
		//No of courses returned by API
		int noOfCourses = response.getInt("courses.size()");
		System.out.println(noOfCourses);
		
		//Purchase Amount
		System.out.println(response.getString("dashboard.purchaseAmount"));
		int purchaseAmount = response.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);
		
		// Title of the first course
		String firstTitle = response.get("courses[0].title");
		System.out.println(firstTitle);
		System.out.println(response.get("courses[0].title").toString());
		
		//Print All course titles and their respective Prices
		for(int i=0; i<noOfCourses; i++) {
			System.out.println("Course title is "+response.get("courses["+i+"].title")+" and Course price is "+response.getString("courses["+i+"].price"));
		}
		
		//Print no of copies sold by RPA Course
		for(int i=0; i<noOfCourses; i++) {
			if(response.getString("courses["+i+"].title").equalsIgnoreCase("RPA")) {
					System.out.println(response.getString("courses["+i+"].copies"));
					break;
			}
		}
		
		
		}
	
}
