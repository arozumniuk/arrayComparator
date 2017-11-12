package main.java.Managers;


import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiManager {

    public Response sendGet(String url) {

        return RestAssured.given()
                .when()
                .get(url);

    }
}
