package api.test;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.user;
import io.restassured.response.Response;

public class UserTests {

	//in this method I will generate the data using Faker Library and passing to POJO class
	//same data will also pass to the post request
	//creating faker variable and POJO user variable.
	
	Faker faker;
	user UserPayload;
	
	public Logger logger;
	
	@BeforeClass
	public void setup() {
		
		//create a faker and userpayload objects
		faker = new Faker();
		UserPayload = new user();
		
		//using faker libray we will set userpayload POJO datas
		UserPayload.setId(faker.idNumber().hashCode()); // hascode will generate random ID
		UserPayload.setUsername(faker.name().username());
		UserPayload.setFirstname(faker.name().firstName());
		UserPayload.setLastname(faker.name().lastName());
		UserPayload.setEmail(faker.internet().safeEmailAddress());
		UserPayload.setPassword(faker.internet().password());
		UserPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger = LogManager.getLogger(this.getClass());
		
	}
		
	//now creating test methods for user CRUD Requests
	
		@Test(priority=1) //create user
		public void createUser()
		{
			logger.info("******** Create User ************");
			Response response = UserEndPoints.createUser(UserPayload);
			response.then().log().all();
			
			Assert.assertEquals(response.getStatusCode(), 200);
			logger.info("******** User is Created ************");
		}
		
		@Test(priority=2) // read user
		public void getUserbyname()
		{
			
			logger.info("******** Getting User Info ************");	
			 Response response =UserEndPoints.ReadUser(this.UserPayload.getUsername());
			 response.then().log().all();
			 Assert.assertEquals(response.statusCode(), 200);
			 logger.info("******** User INfo is displayed ************");
		}
		
		@Test(priority=3)
		public void UpdateUserbyname() {
			
			logger.info("******** Update User ************");
			//Update user data which you want to update
			UserPayload.setFirstname(faker.name().firstName());
			UserPayload.setLastname(faker.name().lastName());
			UserPayload.setEmail(faker.internet().safeEmailAddress());
			
			Response response = UserEndPoints.updateUser(this.UserPayload.getUsername(),UserPayload);
			
			//restassured assertion for status code
			//response.then().log().body().statusCode(200);
			
			response.then().log().body();
			
			//testNG assertion for status code
			Assert.assertEquals(response.getStatusCode(), 200);
			
			//Checking Response Body data after user update
			Response responseAfterUpdate =UserEndPoints.ReadUser(this.UserPayload.getUsername());
			response.then().log().body();
			Assert.assertEquals(responseAfterUpdate.statusCode(), 200);
			
			logger.info("******** User is Updated ************");
			
		}
		
		@Test (priority=4)
		public void deleteUserbyName() {
			
			logger.info("******** Delete User ************");
			Response response = UserEndPoints.deleteUser(this.UserPayload.getUsername());
			Assert.assertEquals(response.statusCode(), 200);
			logger.info("******** User is deleted ************");
		}

		
}
