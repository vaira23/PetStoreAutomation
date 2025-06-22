package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints2 {
	
	static ResourceBundle getURL(){
		
		ResourceBundle routes=ResourceBundle.getBundle("routes");	//load properties file
		return routes;
	}

	public static Response createUser(User userPayload) {
		
		String post_url=getURL().getString("post_URL");
		
        return given()
            .contentType(ContentType.JSON)
            .body(userPayload)
        .when()
            .post(post_url);
    }

    public static Response readUser(String username) {
    	
    	String gett_url=getURL().getString("get_URL");
    	
        return given()
            .pathParam("username", username)
        .when()
            .get(gett_url); 
    }
	
	public static Response updateUser(String userName, User payload){
		
		String update_url=getURL().getString("update_URL");
		
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", userName)
				.body(payload)
			
			
		.when()
			.put(update_url);
		
		return response;
	}
	
	public static Response deleteUser(String userName){
		
		String delete_url=getURL().getString("delete_URL");
		
		Response response=given()
				.pathParam("username", userName)
			
		.when()
			.delete(delete_url);
		
		return response;
	}

}
