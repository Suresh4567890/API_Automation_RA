package tests;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class Put_Auth {


	@Test
	public void updatePut() {

		//Define the Endpoint
		RestAssured.baseURI="https://gorest.co.in/public/v2/users";

		//Specify the request
		RequestSpecification request = RestAssured.given();

		//Aythorization
		String token ="cd78e76581358de3a25704afa79e6209f3404b7c3316fda0f0e7bf2e7a3b1b27";

		request.header("Content-Type","application/json");
		request.header("Authorization","Bearer "+token);

		JSONObject input = new JSONObject();

		input.put("name","Testuser30");
		input.put("email","testuser30@gmail.com");
		input.put("gender","male");
		input.put("status","active");

		request.body(input.toString());

		//sumbit the request
		Response response = request.request(Method.PUT,"/5783010");
		
		String r_body = response.getBody().asString();
		System.out.println("response body:" +r_body);
		
		int statusCode = response.statusCode();
		Assert.assertEquals(statusCode, 200);
		
		String statusLine = response.getStatusLine();
		System.out.println("statusline:" +statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
		String gender = response.jsonPath().getString("gender");
		System.out.println("Gender:" +gender);
		Assert.assertEquals(gender,"male");
		
		String status = response.jsonPath().getString("status");
		System.out.println("Status is:" +status);
		Assert.assertEquals(status, "active");
		
		String name = response.jsonPath().getString("name");
		System.out.println("Status is:" +name);
		Assert.assertEquals(name,"Testuser30");
		
		String email = response.jsonPath().getString("email");
		System.out.println("Status is:" +email);
		Assert.assertEquals(email, "testuser30@gmail.com");
		
		
		
		
		
		

	}

}
