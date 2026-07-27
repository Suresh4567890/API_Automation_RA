package com.datadriven;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteData_DD {


	@Test(dataProvider="deletedata")
	public void deleteRequest(String id) {

		//Define the Endpoint
		RestAssured.baseURI="https://gorest.co.in/public/v2/users";

		//Specify the request
		RequestSpecification request = RestAssured.given();

		//Aythorization
		String token ="cd78e76581358de3a25704afa79e6209f3404b7c3316fda0f0e7bf2e7a3b1b27";

		request.header("Content-Type","application/json");
		request.header("Authorization","Bearer "+token);
		
		Response response = request.request(Method.DELETE,"/"+id);
		
		String body = response.getBody().asString();
		System.out.println("Response body:" +body);
		
		int statusCode = response.statusCode();
		System.out.println(statusCode);
		Assert.assertEquals(statusCode,204);
		
		String statusLine = response.statusLine();
		System.out.println(statusLine);
		Assert.assertEquals(statusLine,"HTTP/1.1 204 No Content");
  
	}
	
	@DataProvider(name="deletedata")
	public Object[][] getTable() throws IOException {
		
		return DataLibrary.dataRead();
		
		
	}

}
