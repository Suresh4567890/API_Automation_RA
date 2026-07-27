package com.datadriven;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class Put_Datadriven {


	@Test(dataProvider="putdata")
	public void updatePut(String name,String email,String gender,String status,String id) {

		//Define the Endpoint
		RestAssured.baseURI="https://gorest.co.in/public/v2/users";

		//Specify the request
		RequestSpecification request = RestAssured.given();

		//Aythorization
		String token ="cd78e76581358de3a25704afa79e6209f3404b7c3316fda0f0e7bf2e7a3b1b27";

		request.header("Content-Type","application/json");
		request.header("Authorization","Bearer "+token);

		JSONObject input = new JSONObject();

		input.put("name",name);
		input.put("email",email);
		input.put("gender",gender);
		input.put("status",status);

		request.body(input.toString());

		//sumbit the request
		Response response = request.request(Method.PUT,"/"+id);
		
		String r_body = response.getBody().asString();
		System.out.println("response body:" +r_body);
		
		int statusCode = response.statusCode();
		Assert.assertEquals(statusCode, 200);
		
		String statusLine = response.getStatusLine();
		System.out.println("statusline:" +statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
		//Data validation
		
		String name_value = response.jsonPath().getString("name");
		System.out.println("Status is:" +name_value);
		Assert.assertEquals(name_value,name);
		
		String email_value = response.jsonPath().getString("email");
		System.out.println("Status is:" +email_value);
		Assert.assertEquals(email_value, email);
		
		String gender_value = response.jsonPath().getString("gender");
		System.out.println("Gender:" + gender_value);
		Assert.assertEquals(gender_value,gender);
		
		String status_value = response.jsonPath().getString("status");
		System.out.println("Status is:" +status_value);
		Assert.assertEquals(status_value, status);
		
	}
	
	@DataProvider(name="putdata")
	public Object[][] getTable() throws IOException {
		
		return DataLibrary.dataRead();
		
		
	}

}
