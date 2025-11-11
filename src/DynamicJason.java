import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import FilePayload.Payload;
import ReUseableMethod.ReuseableJavaMethod;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJason {
	public void AddBook() {
		RestAssured.baseURI="http://216.10.245.166";
		String response=given().log().all().header("Contest-Type","application/json").
		body(Payload.AddBook("asdfg","123457")).
		when().post("Library/Addbook.php").
		then().log().all().assertThat().statusCode(200).extract().asString();
		JsonPath js=ReuseableJavaMethod.RowToJson(response);
		System.out.println(response);
		String id=js.getString("ID");
		System.out.println(id);
	}
	@Test(dataProvider="data")
	public void AddBook1(String isbn, String aisle) {
		RestAssured.baseURI="http://216.10.245.166";
		String response=given().log().all().header("Contest-Type","application/json").
		body(Payload.AddBook(isbn,aisle)).
		when().post("Library/Addbook.php").
		then().log().all().assertThat().statusCode(200).extract().asString();
		JsonPath js=ReuseableJavaMethod.RowToJson(response);
		System.out.println(response);
		String id=js.getString("ID");
		System.out.println(id);
	}
	@DataProvider(name="data")
	public Object[][] getData() {
		//Single dymension Array: - single set(types) of data.
		//Multidymension Array: - multiple set of Array.
		return new Object[][] {{"asdfghjkl","1234567890"},{"zxcvbnm","1230987"},{"qwertyuiop","567821890"}};
	}
}
