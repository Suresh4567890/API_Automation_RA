package com.chainrequest;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.datadriven.DataLibrary;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


import org.testng.annotations.Listeners;

@Listeners({utilities.ExtentReportManager.class })
public class POST_GET_Chaining {


	@Test(priority=1,dataProvider="postdata")
	public void createPOST(String name,String email,String gender,String status) {
		
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
		Response response = request.request(Method.POST);

		String r_body = response.getBody().asString();
		System.out.println("response body:" +r_body);

		int statusCode = response.statusCode();

		Assert.assertEquals(statusCode, 201);

		String statusLine = response.getStatusLine();
		System.out.println("statusline:" +statusLine);
		//Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
		String id = response.jsonPath().getString("id");
		System.out.println("id is: "+id);
		
		//sumbit get 
	
		RestAssured.baseURI="https://gorest.co.in/public/v2/users";
		RequestSpecification request02 = RestAssured.given();
		
		request02.header("Authorization","Bearer "+token);

        Response response2 = request02.request(Method.GET,"/"+id);

		String res_body = response2.getBody().asString();                //responsebody
		System.out.println("Response Body is:"+res_body);
		
		int statusCode1 = response2.statusCode();
		System.out.println("statuscode is: "+statusCode1);
		
	}
	
	
	@Test(priority=2)
	public void deleteRequest() {

		//Define the Endpoint
		RestAssured.baseURI="https://gorest.co.in/public/v2/users";

		//Specify the request
		RequestSpecification request = RestAssured.given();

		//Aythorization
		String token ="cd78e76581358de3a25704afa79e6209f3404b7c3316fda0f0e7bf2e7a3b1b27";

		request.header("Content-Type","application/json");
		request.header("Authorization","Bearer "+token);
		
		Response response = request.request(Method.DELETE,"/5710536");
		
		String body = response.getBody().asString();
		System.out.println("Response body:" +body);
		
		int statusCode = response.statusCode();
		System.out.println(statusCode);
		Assert.assertEquals(statusCode,204);
		
		String statusLine = response.statusLine();
		System.out.println(statusLine);
		Assert.assertEquals(statusLine,"HTTP/1.1 204 No Content");




	}

	
	
	
	
	
	
	@DataProvider(name="postdata")
	public Object[][] getTable() throws IOException {

		return DataLibrary.dataRead();
    }
	
	

	
	
	

}
