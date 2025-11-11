package ReUseableMethod;

import FilePayload.DummyResponse;
import FilePayload.Payload;
import io.restassured.path.json.JsonPath;

public class ReuseableJavaMethod {
	
	public static JsonPath RowToJson(String response) {
		
		JsonPath js=new JsonPath(response);
		return js;
	}
	
public static JsonPath DummyRowToJson(String response) {
		
		JsonPath js=new JsonPath(Payload.DummyResponse());
		return js;
	}

}
