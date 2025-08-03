package twarita.restAssured.tests;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import twarita.restAssured.files.Payload;
import twarita.restAssured.files.ReusableFiles;

public class DataParameterization {
	
	
	
	
	@Test(enabled = false)
	public void addBook() {
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		String addBokoResponse = given().header("Content-Type","application/json").body(Payload.addBook("gjsskh", "jsgdjka"))
		.when().post("/Library/Addbook.php").
		then().extract().response().asString();
		
		JsonPath js =ReusableFiles.rawToJSON(addBokoResponse);
		System.out.println(js.getString("ID"));
	}
	
	@Test(dataProvider = "bookData", enabled = false)
	public void addBookWithTestNGDataProvider(String isbn, String aisle) {
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		String addBokoResponse = given().header("Content-Type","application/json").body(Payload.addBook(isbn, aisle))
		.when().post("/Library/Addbook.php").
		then().extract().response().asString();
		
		JsonPath js =ReusableFiles.rawToJSON(addBokoResponse);
		System.out.println(js.getString("ID"));
	}
	
	@Test(dataProvider = "bookData", enabled = false)
	public void deleteBookWithTestNGDataProvider(String isbn, String aisle) {
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		String addBokoResponse = given().header("Content-Type","application/json").body(Payload.addBook(isbn, aisle))
		.when().post("/Library/Addbook.php").
		then().extract().response().asString();
		
		JsonPath js =ReusableFiles.rawToJSON(addBokoResponse);
		String bookId = js.getString("ID");
		
		String deletBookResponse = given().header("Content-Type","application/json").
				body("{ \r\n"
						+ "\"ID\" : \""+bookId+"\" \r\n"
						+ "} \r\n"
						+ "").when().post("/Library/DeleteBook.php").then().extract().asString();
		
		JsonPath dltBookJs = ReusableFiles.rawToJSON(deletBookResponse);
		System.out.println(dltBookJs.getString("msg"));
		
	}
	
	@Test
	public void addBookWithExternalFiles() throws IOException {
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		String addBokoResponse = given().header("Content-Type","application/json").
				body(new String(Files.readAllBytes(Paths.get("C:\\Users\\twari\\OneDrive\\Documents\\addBookPayload.txt"))))
		.when().post("/Library/Addbook.php").
		then().extract().response().asString();
		
		JsonPath js =ReusableFiles.rawToJSON(addBokoResponse);
		System.out.println(js.getString("ID"));
	}
	
	@DataProvider(name = "bookData")
	public Object[][] getData(){
		return new Object[][] {{"aff", "fsfs"}, {"sdfsf", "fdssfsfs"}, {"csfsf", "fsdgd"}};
	}

}
