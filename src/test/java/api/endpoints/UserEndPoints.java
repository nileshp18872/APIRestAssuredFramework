package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.user;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//UserEndpoints.java
//created for perform Create,Read,Update,Delete UserModule Requests

public class UserEndPoints {
	
	//UserEndPoint - create user implementation by passing payload
	public static Response createUser(user payload){
		
		Response response = given()
		   .contentType(ContentType.JSON)
		   .accept(ContentType.JSON)
		   .body(payload)
		   
		.when()
		   .post(Routes.post_url);
		
		return response;
		
	}
	
	
	//UserEndPoint - Read user implementation by passing username parameter
		public static Response ReadUser(String username){
			
			Response response = given()
			   .pathParam("username", username)
			   
			.when()
			   .get(Routes.get_url);
			
			return response;
			
		}
		
		
		//UserEndPoint - Update user implementation by passing payload and username parameter
		public static Response updateUser(String username, user payload){
			
			Response response = given()
			   .contentType(ContentType.JSON)
			   .accept(ContentType.JSON)
			   .pathParam("username", username)
			   .body(payload)
			   
			.when()
			   .put(Routes.put_url);
			
			return response;
			
		}	
		
		//UserEndPoint - delete user implementation by passing username parameter
				public static Response deleteUser(String username){
					
					Response response = given()
					   .pathParam("username", username)
					   
					.when()
					   .delete(Routes.delete_url);
					
					return response;
					
				}
	
	

}
