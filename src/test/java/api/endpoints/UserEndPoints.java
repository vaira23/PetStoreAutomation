package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


//for CRUD operations
public class UserEndPoints {

	public static Response createUser(User userPayload) {
        return given()
            .contentType(ContentType.JSON)
            .body(userPayload)
        .when()
            .post(Routes.post_URL);
    }

    public static Response readUser(String username) {
        return given()
            .pathParam("username", username)
        .when()
            .get(Routes.get_url); 
    }
	
	public static Response updateUser(String userName, User payload){
		
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", userName)
				.body(payload)
			
			
		.when()
			.put(Routes.update_url);
		
		return response;
	}
	
	public static Response deleteUser(String userName){
		
		Response response=given()
				.pathParam("username", userName)
			
		.when()
			.delete(Routes.delete_url);
		
		return response;
	}
}