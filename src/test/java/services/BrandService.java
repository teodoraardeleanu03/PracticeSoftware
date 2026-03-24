package services;

import client.RestClient;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.RequestBrandModel;
import models.ResponseBrandModel;
import org.testng.Assert;
import types.EndpointType;
import types.RequestMethodType;
import types.ResponseStatusType;

public class BrandService {
    public ResponseBrandModel createBrand(RequestBrandModel requestBody) {
        System.out.println("STEP 1: CREATE NEW BRAND");
        RequestSpecification request = RestAssured.given();
        request.body(requestBody);
        Response response = performRequest(RequestMethodType.REQUEST_POST, request, EndpointType.BRAND_CREATE_ENDPOINT);
        System.out.println(response.getStatusLine());
        response.body().prettyPrint();
        Assert.assertEquals(response.getStatusCode(), ResponseStatusType.RESPONSE_CREATED);
        return response.getBody().as(ResponseBrandModel.class);
    }

    public void checkSpecificBrand(String brandId) {
        System.out.println("STEP 2: CHECK BRAND REQUEST");
        RequestSpecification request = RestAssured.given();

        request.header("Authorization", "Bearer " + brandId);
        Response response = performRequest(RequestMethodType.REQUEST_GET, request, EndpointType.BRAND_REQUEST_ENDPOINT + brandId);
        System.out.println(response.getStatusLine());
        response.body().prettyPrint();
        Assert.assertEquals(response.getStatusCode(), ResponseStatusType.RESPONSE_OK);
    }

    public void checkUser(String token, String userId, int statusCode) {
        System.out.println("STEP 3: CHECK USER REQUEST");
        RequestSpecification request = RestAssured.given();

        request.header("Authorization", "Bearer " + token);
        Response response = performRequest(RequestMethodType.REQUEST_GET, request, EndpointType.USER_SPECIFIC_ENDPOINT + userId);
        System.out.println(response.getStatusLine());
        response.body().prettyPrint();
        Assert.assertEquals(response.getStatusCode(), statusCode);
    }

    public void updateBrand(String brandId, RequestBrandModel requestBody) {
        System.out.println("STEP 3: UPDATE BRAND");
        RequestSpecification request = RestAssured.given();
        request.body(requestBody);
        Response response = performRequest(RequestMethodType.REQUEST_PUT, request, EndpointType.BRAND_REQUEST_ENDPOINT + brandId);
        System.out.println(response.getStatusLine());
        response.body().prettyPrint();
        Assert.assertEquals(response.getStatusCode(), ResponseStatusType.RESPONSE_OK);
    }

    public void deleteBrand(String token, String brandId) {
        System.out.println("STEP 6: DELETE BRAND REQUEST");
        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "Bearer " + token);
        Response response = performRequest(RequestMethodType.REQUEST_DELETE, request, EndpointType.BRAND_REQUEST_ENDPOINT + brandId);
        System.out.println(response.getStatusLine());
        response.body().prettyPrint();
        Assert.assertEquals(response.getStatusCode(), ResponseStatusType.RESPONSE_NO_CONTENT);
    }

    public void checkBrandDeleted(String token, String brandId) {
        System.out.println("STEP 7: CHECK BRAND DELETED REQUEST");
        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "Bearer " + token);
        Response response = performRequest(RequestMethodType.REQUEST_GET, request, EndpointType.BRAND_REQUEST_ENDPOINT + brandId);
        System.out.println(response.getStatusLine());
        response.body().prettyPrint();
        Assert.assertEquals(response.getStatusCode(), ResponseStatusType.RESPONSE_NOT_FOUND);
    }

    private Response performRequest(String requestType, RequestSpecification request, String endpoint) {
        return new RestClient().performRequest(requestType, request, endpoint);
    }
}
