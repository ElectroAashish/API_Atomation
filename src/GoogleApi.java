import org.testng.annotations.Test;

import POJO.GoogleApiAddPlace;
import POJO.LocationGoogleApiAddPlace;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class GoogleApi {
	@Test
	public static void AddPlace() {
		GoogleApiAddPlace GoogleApiAddPlace=new GoogleApiAddPlace();
		LocationGoogleApiAddPlace Location=GoogleApiAddPlace.getLocation();
		Location.setLat(-38.383494);
		Location.setLng(33.427362);
		GoogleApiAddPlace.setAccuracy(50);
		GoogleApiAddPlace.setName("Aashish Yadav");
		GoogleApiAddPlace.setPhone_number("(+91) 983 893 3937");
		GoogleApiAddPlace.setAddress("Yadav Basti, Dhanera");
		List<String> types=new ArrayList<String>();
		types.add("shoe park");
		types.add("shop");
		GoogleApiAddPlace.setTypes(types);
		GoogleApiAddPlace.setWebsite("http://google.com");
		GoogleApiAddPlace.setLanguage("French-IN");
		
	RestAssured.baseURI="https://rahulshettyacademy.com";
	
	ValidatableResponse serigilizationResponse=given().log().all().queryParam("key", "qaclick123")
	.body(GoogleApiAddPlace)
	.when().post("/maps/api/place/add/json")
	.then().log().all().assertThat().statusCode(200);
	
	System.out.println(serigilizationResponse);
	}
}
