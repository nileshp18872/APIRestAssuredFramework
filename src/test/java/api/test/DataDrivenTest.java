package api.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.user;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTest {

	//create all users from the DataProvider Data & createUser Endpoint
	@Test(priority=1,dataProvider="Data", dataProviderClass=DataProviders.class) 
	public void createUsers(String uid, String username, String first, String last, String email, String pwd, String phone)
	{
		user userPayload = new user();
		
		userPayload.setId(Integer.parseInt(uid)); 
		userPayload.setUsername(username);
		userPayload.setFirstname(first);
		userPayload.setLastname(last);
		userPayload.setEmail(email);
		userPayload.setPassword(pwd);
		userPayload.setPhone(phone);
		
		Response response = UserEndPoints.createUser(userPayload);
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	//get all users from the DataProvider Usernames & readuser endpoint
	@Test(priority=2,dataProvider="UserNames", dataProviderClass=DataProviders.class) // read user
	public void getUsersbyname(String username)
	{
		
				
		 Response response =UserEndPoints.ReadUser(username);
		 Assert.assertEquals(response.statusCode(), 200);
	}
	
	//Update all users from the DataProvider Data & updateuser endpoint
	@Test(priority=3,dataProvider="Data", dataProviderClass=DataProviders.class)
	public void UpdateUserbyname(String uid, String username, String first, String last, String email, String pwd, String phone) {
		
		user userPayload = new user();
		
		userPayload.setId(Integer.parseInt(uid)); 
		userPayload.setUsername(username);
		userPayload.setPassword(pwd);
		userPayload.setPhone(phone);
		
		//Update user data which you want to update
		userPayload.setFirstname(first+" Edited");
		userPayload.setLastname(last+" Edited");
		userPayload.setEmail("Edited"+email);
		
		Response response = UserEndPoints.updateUser(username,userPayload);
		
		//restassured assertion for status code
		//response.then().log().body().statusCode(200);
		
		response.then().log().body();
		
		//testNG assertion for status code
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//Checking Response Body data after user update
		Response responseAfterUpdate =UserEndPoints.ReadUser(username);
		response.then().log().body();
		Assert.assertEquals(responseAfterUpdate.statusCode(), 200);
		
	}

	
	//delete all users from the DataProvider Usernames & deleteuser endpoint
	@Test(priority=4,dataProvider="UserNames", dataProviderClass=DataProviders.class) 
	public void deleteusers(String username) {
		Response response = UserEndPoints.deleteUser(username);
		Assert.assertEquals(response.statusCode(), 200);
	}
	
}
