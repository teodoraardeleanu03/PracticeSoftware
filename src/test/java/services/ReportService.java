package services;

import client.RestClient;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import types.EndpointType;
import types.RequestMethodType;
import types.ResponseStatusType;
import utils.LogUtility;

public class ReportService {
    public void generateAverageSalesPerMonthReport(String token) {
        LogUtility.infoLog("STEP 2: GENERATE REPORT");
        RequestSpecification request = RestAssured.given();

        request.header("Authorization", "Bearer " + token);
        Response response = performRequest(RequestMethodType.REQUEST_GET, request, EndpointType.REPORT_AVERAGE_SALES_ENDPOINT);
        LogUtility.infoLog(response.getStatusLine());
        response.body().prettyPrint();
        Assert.assertEquals(response.getStatusCode(), ResponseStatusType.RESPONSE_OK);
    }

    private Response performRequest(String requestType, RequestSpecification request, String endpoint) {
        return new RestClient().performRequest(requestType, request, endpoint);
    }
}
