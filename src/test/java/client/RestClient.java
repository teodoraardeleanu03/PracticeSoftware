package client;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {
    // trebuie sa fac 2 actiuni pe aceasta clasa
    // trebuie sa configurez clientul
    // pe baza configurarilor trebuie sa pot sa execut orice actiune (get, post, put, delete)

    public Response performRequest(String requestType, RequestSpecification request, String endpoint) {
        switch (requestType) {
            case "POST":
                prepareClient(request).post(endpoint);
                break;
            case "GET":
                prepareClient(request).get(endpoint);
                break;
            case "PUT":
                prepareClient(request).put(endpoint);
                break;
            case "DELETE":
                prepareClient(request).delete(endpoint);
                break;
        }
        return null;
    }

    public RequestSpecification prepareClient(RequestSpecification request) {
        RestAssured.baseURI = "https://api.practicesoftwaretesting.com";
        request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Accept", "application/json");

        return request;
    }
}
