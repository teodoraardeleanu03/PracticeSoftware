package services;

import client.RestClient;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.*;
import org.testng.Assert;

public class UserService {
    // aceasta clasa reprezinta metodele de la serviciul User de pe Swagger
    public ResponseUserModel createUser(RequestUserModel requestBody) {
        System.out.println("STEP 1: CREATE USER REQUEST");
        RequestSpecification request = RestAssured.given();
        request.body(requestBody);

        Response response = performRequest("POST", request, "/users/register");
        System.out.println(response.getStatusLine());
        response.body().prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 201);

        return response.getBody().as(ResponseUserModel.class);
    }

    public ResponseUserLoginModel loginUser(RequestUserModel requestBody) {
        System.out.println("STEP 2: LOGIN USER REQUEST");
        RequestUserLoginModel requestLoginBody = new RequestUserLoginModel(requestBody.getEmail(), requestBody.getPassword());
        RequestSpecification request = RestAssured.given();

        request.body(requestLoginBody);

        Response response = performRequest("POST", request, "/users/login");
        System.out.println(response.getStatusLine());
        response.body().prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200);

        return response.getBody().as(ResponseUserLoginModel.class);
    }

    public void checkUser(String token, String userId, int statusCode) {
        System.out.println("STEP 3: CHECK USER REQUEST");
        RequestSpecification request = RestAssured.given();

        request.header("Authorization", "Bearer " + token);
        Response response = performRequest("GET", request, "/users/" + userId);
        System.out.println(response.getStatusLine());
        response.body().prettyPrint();
        Assert.assertEquals(response.getStatusCode(), statusCode);
    }

    public void logoutUser(String token) {
        System.out.println("STEP 4: LOGOUT USER REQUEST");
        RequestSpecification request = RestAssured.given();

        request.header("Authorization", "Bearer " + token);
        Response response = performRequest("GET", request, "/users/logout");
        System.out.println(response.getStatusLine());
        response.body().prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    public ResponseUserLoginModel loginUser(RequestUserLoginModel requestBody) {
        System.out.println("STEP 5: LOGIN USER ADMIN REQUEST");
        RequestSpecification request = RestAssured.given();
        request.body(requestBody);

        Response response = performRequest("POST", request, "/users/login");
        System.out.println(response.getStatusLine());
        response.body().prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200);

        return response.getBody().as(ResponseUserLoginModel.class);
    }

    public void deleteUser(String token, String userId) {
        System.out.println("STEP 6: DELETE USER REQUEST");
        RequestSpecification request = RestAssured.given();

        request.header("Authorization", "Bearer " + token);
        Response response = performRequest("DELETE", request, "/users/" + userId);
        System.out.println(response.getStatusLine());
        response.body().prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 204);
    }

    private Response performRequest(String requestType, RequestSpecification request, String endpoint) {
        return new RestClient().performRequest(requestType, request, endpoint);
    }
}
