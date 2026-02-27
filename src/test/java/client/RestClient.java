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
                return prepareClient(request).post(endpoint);
            case "GET":
                return prepareClient(request).get(endpoint);
            case "PUT":
                return prepareClient(request).put(endpoint);
            case "DELETE":
                return prepareClient(request).delete(endpoint);
        }
        return null;
    }

    public RequestSpecification prepareClient(RequestSpecification request) {
        request.baseUri("https://api.practicesoftwaretesting.com");
        request.header("Content-Type", "application/json");
        request.header("Accept", "application/json");

        return request;
    }
}
