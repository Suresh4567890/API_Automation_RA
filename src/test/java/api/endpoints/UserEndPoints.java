package api.endpoints;

import api.payload.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserEndPoints {



	public static Response createUser(User payload) {

		Response response = RestAssured.
				given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.when()
				.post(Routers.post_url);

		return  response;
	}
	
	
	public static Response getUser(String username) {

		Response response = RestAssured.
				given()
				.pathParam("username", username)
				.when()
				.get(Routers.get_url);

		return  response;
	}
	
	
	public static Response updateUser(String username,User payload) {

		Response response = RestAssured.
				given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", payload)
				.body(payload)
				.when()
				.put(Routers.put_url);

		return  response;
	}
	
	
	public static Response deleteUser(String username) {

		Response response = RestAssured.
				given()
				.pathParam("username", username)
				.when()
				.delete(Routers.delete_url);

		return  response;
	}







}

