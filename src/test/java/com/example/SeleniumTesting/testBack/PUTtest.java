package com.example.SeleniumTesting.testBack;

import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.time.LocalDate;

import static org.hamcrest.Matchers.*;




import static io.restassured.RestAssured.given;

public class PUTtest {

    @Test
    public void Put_test01() {
        JsonObject request = new JsonObject();
        request.addProperty("name", "SPACE");
        request.addProperty("job", "CAMBIADO por restassure");

        given().contentType("application/json").body(request)
                .put("https://reqres.in/api/users/2").then().statusCode(200)
                .log().status().log().body();
    }

    @Test
    public void Put_test02() {
        JsonObject requestBody = new JsonObject();
        String newName = "SPACE";
        String newJob = "CAMBIADO por restassure";
        requestBody.addProperty("name", newName);
        requestBody.addProperty("job", newJob);


        Response responseBody =   given().contentType("application/json").body(requestBody)
                .put("https://reqres.in/api/users/2");



        // Extraemos el name del json
        String nombreModificado = responseBody.jsonPath().getString("name");
        String trabajoModificado = responseBody.jsonPath().getString("job");

        Assert.assertEquals(nombreModificado,newName);
        Assert.assertEquals(trabajoModificado,newJob);

        given().contentType("application/json").body(requestBody).put("https://reqres.in/api/users/2")
                .then().body("updatedAt",containsString("2024-01-15"));

    }
}
