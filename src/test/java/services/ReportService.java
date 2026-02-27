package services;

import client.RestClient;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

public class ReportService {
    public void generateAverageSalesPerMonthReport(String token) {
        System.out.println("STEP 2: GENERATE REPORT");
        RequestSpecification request = RestAssured.given();

        request.header("Authorization", "Bearer " + token);
        Response response = performRequest("GET", request, "/reports/average-sales-per-month" );
        System.out.println(response.getStatusLine());
        response.body().prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    private Response performRequest(String requestType, RequestSpecification request, String endpoint) {
        return new RestClient().performRequest(requestType, request, endpoint);
    }
}
