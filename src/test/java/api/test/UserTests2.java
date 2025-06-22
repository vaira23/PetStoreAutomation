package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests2 {

	
	Faker faker;
    User userPayload;
    public Logger logger;

    @BeforeClass
    public void setUp() {
       
    	faker = new Faker();
        userPayload = new User();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().emailAddress());
        userPayload.setPassword(faker.internet().password(6, 8));
        userPayload.setPhone(faker.phoneNumber().cellPhone());  // Make sure phone is of type String in your User class
        
        //logs 
        logger=LogManager.getLogger(this.getClass());
        
      /*  logger.debug("Debugging.....");	*/
        
    }

    @Test(priority = 1)
    public void testPostUser() {
       
    	logger.info("***** Creating user *****");
    	
    	Response response = UserEndpoints2.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        
        logger.info("***** User is Created *****");
    }

    @Test(priority = 2)
    public void testGetUserByName() {
       
    	logger.info("***** Reading User Info *****");
    	
    	System.out.println("Getting user with username: " +this.userPayload.getUsername());
        Response response = UserEndpoints2.readUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        
        logger.info("***** User Info is Displayed *****");
    }
    
    @Test(priority = 3)
    public void testUpdateByName() {
    	
    	logger.info("***** Updating User Info *****");
    	
    	//user data using payload
    	userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().emailAddress());
        
        Response response = UserEndpoints2.updateUser(this.userPayload.getUsername(), userPayload);
        response.then().log().body().statusCode(200);
        
        
        Assert.assertEquals(response.getStatusCode(), 200);
        
        logger.info("***** User Info Updated *****");
        
        //checking after update
        Response responseAfterUpdate = UserEndpoints2.readUser(this.userPayload.getUsername());
       // response.then().log().all();
        Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
    }
    
    @Test(priority = 4)
    public void testDeleteUserByName() {
    	
    	logger.info("***** Deleting User Info *****");
    	
    	Response response=UserEndpoints2.deleteUser(this.userPayload.getUsername());
    	Assert.assertEquals(response.getStatusCode(), 200);
    	
    	logger.info("*****  User Info Deleted *****");
    }

}
