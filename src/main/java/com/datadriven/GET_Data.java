package com.datadriven;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class GET_Data {


	@Test(dataProvider="getdata")
	public void GET_Auth(String id) {

		//Define the Endpoint
		RestAssured.baseURI="https://gorest.co.in/public/v2/users";

		//Specify the request
		RequestSpecification request = RestAssured.given();

		//Aythorization
		String token ="cd78e76581358de3a25704afa79e6209f3404b7c3316fda0f0e7bf2e7a3b1b27";
		request.header("Authorization","Bearer "+token);

		//sumbit the request
		Response response = request.request(Method.GET,"/"+id);

		String res_body = response.getBody().asString();                //responsebody
		System.out.println("Response Body is:"+res_body);
		
		int statusCode = response.statusCode();
		System.out.println("statuscode is: "+statusCode);

		//Data Validation
	/*	String name = response.jsonPath().getString("name");                
		Assert.assertEquals("ChotaBheem",name);

		if(name.equalsIgnoreCase("ChotaBheem"))
		{
			System.out.println("Value for the field:name matches successfully");
		}
		else
		{
			Assert.fail("Mismatch in the values");
		}
		
		String contentType = response.contentType();                       //contenttype
		System.out.println("content type: "+contentType);
		Assert.assertEquals(contentType, "application/json; charset=utf-8"); */
		
	}
	

	@DataProvider(name="getdata")
	public Object[][] getTable() throws IOException {
		
		return DataLibrary.dataRead();
		
		
	}


}
