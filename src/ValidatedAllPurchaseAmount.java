import org.testng.Assert;
import org.testng.annotations.Test;

import FilePayload.Payload;
import ReUseableMethod.ReuseableJavaMethod;
import io.restassured.path.json.JsonPath;

public class ValidatedAllPurchaseAmount {
	@Test
	public void ValidetedAmount() {
		JsonPath js=ReuseableJavaMethod.RowToJson(Payload.DummyResponse());
		//JsonPath js=new JsonPath(Payload.DummyResponse());
		int purchaseAmount=js.getInt("dashboard.purchaseAmount");
		int size=js.getInt("courses.size()");
		int total=0;
		for(int i=0;i<size;i++) {
			int price=js.getInt("courses["+i+"].price");
			int copies=js.getInt("courses["+i+"].copies");
			total+=price*copies;
		}
		System.out.println(total);
		Assert.assertEquals(total,purchaseAmount);
	}
}
