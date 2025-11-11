import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import FilePayload.Payload;
import ReUseableMethod.ReuseableJavaMethod;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class AddPlacePOST {
	public static void main(String[] args){
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().log().all(). queryParam("key","qaclick123").header("Content-Type","application/json")
		.body(Payload.AddPlace())
		.when().post("maps/api/place/add/json")
		.then().log().all().statusCode(200).body("scope", equalTo("APP")).header("Server","Apache/2.4.52 (Ubuntu)")
		.extract().response().asString();
		
		System.out.println(response);
		
		JsonPath js=new JsonPath(response);
		String place_id=js.getString("place_id");
		System.out.println(place_id);
		
		String NewAddress="Aashish Yadav Dhanera";
		
		given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+place_id+"\",\r\n"
				+ "\"address\":\""+NewAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "")
		.when().put("maps/api/place/update/json")
		.then().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		String updateResponse=given().log().all().queryParam("key","qaclick123").queryParam("place_id",place_id)
		.when().get("maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).body("address", equalTo(NewAddress)).extract().asString();
		
		System.out.println(updateResponse);
		JsonPath js1=ReuseableJavaMethod.RowToJson(updateResponse);
		String ActualAddress=js1.getString("address");
		System.out.println(ActualAddress);
		
		Assert.assertEquals(NewAddress, ActualAddress);
		
		//ReuseableJavaMethod.DummyRowToJson(String)
	}
}