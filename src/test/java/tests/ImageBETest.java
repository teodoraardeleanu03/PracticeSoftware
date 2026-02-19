package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ImageBETest {
    @Test
    public void testMethod() {
        RestAssured.baseURI = "https://api.practicesoftwaretesting.com";
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Accept", "application/json");

        // Pasul 1: Accesam toate imaginile

        System.out.println("STEP 1: GET ALL IMAGES REQUEST");
        Response response = request.get("/images" );
        System.out.println(response.getStatusLine());
        response.body().prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200);

    }
}
