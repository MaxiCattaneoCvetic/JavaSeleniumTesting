package com.example.SeleniumTesting.testBack;



import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class POSTtest {

    @Test
    public void Post_test01() {
        JsonObject request = new JsonObject();
        request.addProperty("name","space");
        request.addProperty("job","leader");

        given().contentType("application/json")
                .body(request).post("https://reqres.in/api/users")
                .then().statusCode(201)
                .log().status() // muestra el codigo de status
                .log().body(); // muestra el body de la solicitud
    }
}
