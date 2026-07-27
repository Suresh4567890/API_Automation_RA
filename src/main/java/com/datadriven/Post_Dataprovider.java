package com.datadriven;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Post_Dataprovider {
	
	
	@Test(dataProvider="fetchdata")
	public void post_Auth(String name,String email,String gender,String status) {
		

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
		
		String response_body = response.getBody().asString();
		System.out.println(response_body);
		
		String id = response.jsonPath().getString("id");
		System.out.println("id is: "+id);
		
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		
		if((statusCode==201))
		{
			System.out.println("StatusCode matches successully ");
		}
		else
		{
			System.out.println("Mismatching Statuscode");
		}

	}
	
	
	@DataProvider(name="fetchdata")
	public Object[][] getTable() throws IOException {
		
		return DataLibrary.dataRead();
		
		
	}

}
