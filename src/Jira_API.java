import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

public class Jira_API {
	@Test
	public void CreateBug() {
		RestAssured.baseURI="https://aashishyadav158.atlassian.net/";
		String createIssueResponse=given().header("Content-Type","application/json")
		.header("Authorization","Basic YWFzaGlzaHlhZGF2MTU4QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjB3QVJ1OC13V0dGNHZWTEl6cDNMdXJWbmxVa3Fxc3FCVGZqaUtxQmd2MS1KSGxWbGpOcHRwVDNUdmNLV0YzVXB4VEN0Z3ZIU0JTSXhRbWtqSEZPZXIyWFcyRXlya01ETFcxOGpzbUNKMG1jSGkyREU3cmpvNGdRQ2hTbGstS2NqVWpTTXJVN1k5OWVocFhUeDRXWFNxdkl1Y2lGNk5WQ0xvLUdUVF9YMmQzSkE9RjZBRTk2ODA=")
		.body("{\r\n"
				+ "  \"fields\": {\r\n"
				+ "    \"project\": {\r\n"
				+ "      \"key\": \"SCRUM\"\r\n"
				+ "    },\r\n"
				+ "    \"summary\": \"UAT/SIT server is not loading.\",\r\n"
				+ "    \"description\": {\r\n"
				+ "      \"type\": \"doc\",\r\n"
				+ "      \"version\": 1,\r\n"
				+ "      \"content\": [\r\n"
				+ "        {\r\n"
				+ "          \"type\": \"paragraph\",\r\n"
				+ "          \"content\": [\r\n"
				+ "            {\r\n"
				+ "              \"type\": \"text\",\r\n"
				+ "              \"text\": \"When we login in UAT/SIT ENV page is not loading\"\r\n"
				+ "            }\r\n"
				+ "          ]\r\n"
				+ "        }\r\n"
				+ "      ]\r\n"
				+ "    },\r\n"
				+ "    \"issuetype\": {\r\n"
				+ "      \"name\": \"Bug\"\r\n"
				+ "    }\r\n"
				+ "  }\r\n"
				+ "}")
		.log().all()
		.when().post("rest/api/3/issue")
		.then().log().all().assertThat().statusCode(201).extract().response().asString();
		JsonPath js=new JsonPath(createIssueResponse);
		String issueId=js.getString("id");
		System.out.println(issueId);
		
		given().pathParam("key", issueId)
				.header("X-Atlassian-Token","no-check")
				.header("Authorization","Basic YWFzaGlzaHlhZGF2MTU4QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjB3QVJ1OC13V0dGNHZWTEl6cDNMdXJWbmxVa3Fxc3FCVGZqaUtxQmd2MS1KSGxWbGpOcHRwVDNUdmNLV0YzVXB4VEN0Z3ZIU0JTSXhRbWtqSEZPZXIyWFcyRXlya01ETFcxOGpzbUNKMG1jSGkyREU3cmpvNGdRQ2hTbGstS2NqVWpTTXJVN1k5OWVocFhUeDRXWFNxdkl1Y2lGNk5WQ0xvLUdUVF9YMmQzSkE9RjZBRTk2ODA=")
				.multiPart("file",new File("D:\\JAVA_Aashish\\Notes\\Lecture02\\XAasgdxjgc,vh..txt"))
				.when().post("rest/api/3/issue/{key}/attachments")
				.then().log().all().assertThat().statusCode(200);
		
		given().log().all().header("Authorization","Basic YWFzaGlzaHlhZGF2MTU4QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjB3QVJ1OC13V0dGNHZWTEl6cDNMdXJWbmxVa3Fxc3FCVGZqaUtxQmd2MS1KSGxWbGpOcHRwVDNUdmNLV0YzVXB4VEN0Z3ZIU0JTSXhRbWtqSEZPZXIyWFcyRXlya01ETFcxOGpzbUNKMG1jSGkyREU3cmpvNGdRQ2hTbGstS2NqVWpTTXJVN1k5OWVocFhUeDRXWFNxdkl1Y2lGNk5WQ0xvLUdUVF9YMmQzSkE9RjZBRTk2ODA=").
		header("Accept","application/json").
		when().get("rest/api/3/issue/"+issueId+"").
		then().log().all().assertThat().statusCode(200);
		
	}
}
