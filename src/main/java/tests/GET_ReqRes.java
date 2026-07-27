package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GET_ReqRes {

	@Test
	public void method_GET() {

		//Define the Endpoint
		RestAssured.baseURI="https://reqres.in/api/users";

		//Specify the request
		RequestSpecification request = RestAssured.given();

		Response response = request.request(Method.GET,"/1");

		//Get ResponseBody
		String responsebody = response.getBody().asString();
		System.out.println("The Response Body is:" +responsebody);

		//Get the status code
		int statusCode = response.getStatusCode();
		System.out.println("Status Code is:" +statusCode);

		Assert.assertEquals(statusCode, 200);
		
		//get the status line
		String statusLine = response.getStatusLine();
		System.out.println("StatusLine:" +statusLine);
		
		Assert.assertEquals("HTTP/1.1 200 OK", statusLine);
		
		//Data validation
		String firstname = response.jsonPath().getString("data.first_name");
		System.out.println("FirstName is:" +firstname);
		Assert.assertEquals(firstname, "George");
		
		String lastname = response.jsonPath().getString("data.last_name");
		System.out.println("LastName is:" +lastname);
		Assert.assertEquals(lastname, "Bluth");
		
		String email = response.jsonPath().getString("data.email");
		System.out.println("Email is:" +email);
		if(email.contains("george"))
		{
			System.out.println("Firstname of email has been verified successfully");
		}
		else
		{
			//System.out.println("Firstname email is not verified");
			Assert.fail("Firstname email is not verified");
		}
		
		
	}
		
		
		
}


