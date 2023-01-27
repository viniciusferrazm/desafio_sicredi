package rest;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public class BaseAPI {

    @BeforeAll
    public static void preCondition(){
        baseURI = "http://localhost";
        port = 8080;
        basePath = "/api/v1";
    }
}
