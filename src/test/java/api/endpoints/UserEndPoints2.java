package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.user;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//UserEndpoints.java
//created for perform Create,Read,Update,Delete UserModule Requests

public class UserEndPoints2 {
	
	//method is Load the property file using resourceBundle class & getting URLS from the properties file.
	public static ResourceBundle getURL(){
		ResourceBundle routes = ResourceBundle.getBundle("routes");
		return routes;
	}
	
	
	//UserEndPoint - create user implementation by passing payload
	public static Response createUser(user payload){
		
		//get the post_url from properties file
		String post_url = getURL().getString("post_url");
		
		Response response = given()
		   .contentType(ContentType.JSON)
		   .accept(ContentType.JSON)
		   .body(payload)
		   
		.when()
		   .post(post_url);
		
		return response;
		
	}
	
	
	//UserEndPoint - Read user implementation by passing username parameter
		public static Response ReadUser(String username){
			
			//get the get_url from properties file
			String get_url = getURL().getString("get_url");
			
			Response response = given()
			   .pathParam("username", username)
			   
			.when()
			   .get(get_url);
			
			return response;
			
		}
		
		
		//UserEndPoint - Update user implementation by passing payload and username parameter
		public static Response updateUser(String username, user payload){
			
			//get the update_url from properties file
			String update_url = getURL().getString("update_url");
			
			Response response = given()
			   .contentType(ContentType.JSON)
			   .accept(ContentType.JSON)
			   .pathParam("username", username)
			   .body(payload)
			   
			.when()
			   .put(update_url);
			
			return response;
			
		}	
		
		//UserEndPoint - delete user implementation by passing username parameter
				public static Response deleteUser(String username){
					//get the delete_url from properties file
					String delete_url = getURL().getString("delete_url");
					
					Response response = given()
					   .pathParam("username", username)
					   
					.when()
					   .delete(delete_url);
					
					return response;
					
				}
	
	

}
