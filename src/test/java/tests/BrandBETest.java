package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.BrandService;
import services.UserService;

public class BrandBETest {
    @Test
    public void userTest() {
        // Pasul 1: Cream un Brand
        RequestBrandModel requestBody = new RequestBrandModel("Brand", "Testing");

        BrandService brandService = new BrandService();
        ResponseBrandModel responseBody = brandService.createBrand(requestBody);

        // Pasul 2: Verificam ca s-a creat brandul
        brandService.checkSpecificBrand(responseBody.getId());

        // Pasul 3 modificam un brand
        RequestBrandModel requestBody2 = new RequestBrandModel("Teodora", "Test");
        brandService.updateBrand(responseBody.getId(), requestBody2);

        //Pasul 4 verificam ca s-a modificat brandul
        brandService.checkSpecificBrand(responseBody.getId());

        // Pasul 5: Ne logam cu userul admin
        UserService userService = new UserService();
        RequestUserLoginModel requestAdminBody = new RequestUserLoginModel("admin@practicesoftwaretesting.com", "welcome01");
        ResponseUserLoginModel responseAdminBody = userService.loginUser(requestAdminBody);

        // Pasul 6: Stergem brandul
        brandService.deleteBrand(responseAdminBody.getAccess_token(), responseBody.getId());

        // Pasul 7: Verificam ca brandul s-a sters
        brandService.checkBrandDeleted(responseAdminBody.getAccess_token(), responseBody.getId());
    }
}
