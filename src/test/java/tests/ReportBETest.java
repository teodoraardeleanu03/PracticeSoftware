package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.RequestUserLoginModel;
import models.ResponseUserLoginModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.ReportService;
import services.UserService;

public class ReportBETest {
    @Test
    public void reportTest() {
        // Pasul 1: Ne logam cu userul admin
        RequestUserLoginModel requestBody= new RequestUserLoginModel("admin@practicesoftwaretesting.com", "welcome01");

        UserService userService = new UserService();
        ResponseUserLoginModel responseBody = userService.loginUser(requestBody);

        // Pasul 2: Generarm raportul de vanzari / luna
        ReportService reportService = new ReportService();
        reportService.generateAverageSalesPerMonthReport(responseBody.getAccess_token());
    }
}
