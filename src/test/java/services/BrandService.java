package services;

import client.RestClient;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.RequestBrandModel;
import models.ResponseBrandModel;
import org.testng.Assert;

public class BrandService {
    public ResponseBrandModel createBrand(RequestBrandModel requestBody) {
        System.out.println("STEP 1: CREATE NEW BRAND");
        RequestSpecification request = RestAssured.given();
        request.body(requestBody);
        Response response = performRequest("POST", request, "/brands");
        System.out.println(response.getStatusLine());
        response.body().prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 201);
        return response.getBody().as(ResponseBrandModel.class);
    }

    public void checkSpecificBrand(String brandId) {
        System.out.println("STEP 2: CHECK BRAND REQUEST");
        RequestSpecification request = RestAssured.given();

        request.header("Authorization", "Bearer " + brandId);
        Response response = performRequest("GET", request, "/brands/" + brandId);
        System.out.println(response.getStatusLine());
        response.body().prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200);
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

    public void updateBrand(String brandId, RequestBrandModel requestBody) {
        System.out.println("STEP 3: UPDATE BRAND");
        RequestSpecification request = RestAssured.given();
        request.body(requestBody);
        Response response = performRequest("PUT", request, "/brands/" + brandId);
        System.out.println(response.getStatusLine());
        response.body().prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    public void deleteBrand(String token, String brandId) {
        System.out.println("STEP 6: DELETE BRAND REQUEST");
        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "Bearer " + token);
        Response response = performRequest("DELETE", request, "/brands/" + brandId);
        System.out.println(response.getStatusLine());
        response.body().prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 204);
    }

    public void checkBrandDeleted(String token, String brandId) {
        System.out.println("STEP 7: CHECK BRAND DELETED REQUEST");
        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "Bearer " + token);
        Response response = performRequest("GET", request, "/brands/" + brandId);
        System.out.println(response.getStatusLine());
        response.body().prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 404);
    }

    private Response performRequest(String requestType, RequestSpecification request, String endpoint) {
        return new RestClient().performRequest(requestType, request, endpoint);
    }
}
