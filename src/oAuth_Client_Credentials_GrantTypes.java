import org.testng.Assert;
import org.testng.annotations.Test;
import POJO.GetApi;
import POJO.GetCources;
import POJO.GetPayload;
import POJO.GetwebAutomation;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.given;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class oAuth_Client_Credentials_GrantTypes {
	@Test
	public void oAuth() {
		String[] ExpectedCources= {"Rest Assured Automation using Java","SoapUI Webservices testing"};
		
		String oAuthResponse=given().log().all()
		.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParam("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.formParam("grant_type","client_credentials")
		.formParam("scope", "trust")
		.when().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath oAuthResponse1=new JsonPath(oAuthResponse);
		String access_token=oAuthResponse1.getString("access_token");
		
		given().log().all()
		.queryParam("access_token", access_token)
		.when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
		.then().log().all().assertThat().statusCode(401);
		
		GetPayload gr=given()
		.queryParam("access_token", access_token)
		.when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
		.as(GetPayload.class);
		
		System.out.println(gr.getlinkedIn());
		System.out.println(gr.getexpertise());
		
		List<GetwebAutomation> WebAutomationCources=gr.getcourses().getWebAutomation();
			for(int i=0;i<WebAutomationCources.size();i++) {
				if(WebAutomationCources.get(i).getCourseTitle().equalsIgnoreCase("Protractor")) {
					System.out.println(WebAutomationCources.get(i).getPrice());
				}
			}
			
			List<GetApi> ApiCources=gr.getcourses().getApi();
			for(int i=0;i<ApiCources.size();i++) {
				System.out.println(ApiCources.get(i).getCourseTitle());
			}
			GetCources allCources=gr.getcourses();
			List<GetApi> ApiCources1=allCources.getApi();
			ArrayList<String> ActualCources=new ArrayList<String>();
			for(int i=0;i<ApiCources1.size();i++) {
				System.out.println(ApiCources.get(i).getPrice());
				ActualCources.add(ApiCources1.get(i).getCourseTitle());
			}
			List<String> ExpectedCources1=Arrays.asList(ExpectedCources);
			Assert.assertTrue(ActualCources.equals(ExpectedCources1));
	}
}