import FilePayload.Payload;
import io.restassured.path.json.JsonPath;
//complex Json parsing.
public class ComplexJsonParsing {
	public static void main(String [] args) {
		JsonPath js=new JsonPath(Payload.DummyResponse());
		//Size of the array present in json.
		int courceCount=js.getInt("courses.size()");
		System.out.println(courceCount);
		//purchase amount field.
		int purchaseAmount=js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);
		//Title of the course present in 1st index.
		String title=js.getString("courses[1].title");
		System.out.println(title);
		//Prices of the course present in 0th index.
		int price=js.getInt("courses[0].price");
		System.out.println(price);
		//Title and Prices of the all courses.
		for(int i=0;i<courceCount;i++) {
			String Title=js.getString("courses["+i+"].title");
			System.out.println(Title);
			int Price=js.getInt("courses["+i+"].price");
			System.out.println(Price);
		}
		//Copies of RPA course 
		for(int i=0;i<courceCount;i++) {
			String title1=js.get("courses["+i+"].title");
			if(title1.equalsIgnoreCase("RPA")) {
				int copies=js.getInt("courses["+i+"].copies");
				System.out.println(copies);
			}
		}
		//Validated all courses amount will be equals to the purchase amount.
		int total=0;
		for(int i=0;i<courceCount;i++) {
			int Price=js.getInt("courses["+i+"].price");
			int copies=js.getInt("courses["+i+"].copies");
			int tem=0;
			tem=Price*copies;
			total=total+tem;
		}
		System.out.println(purchaseAmount+" : "+total);
	}

}
