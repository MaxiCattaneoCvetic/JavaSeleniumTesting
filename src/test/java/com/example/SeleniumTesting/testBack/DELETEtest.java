package com.example.SeleniumTesting.testBack;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class DELETEtest extends BaseTest{


    @Test
    public void DeleteTest_01() {
        Response responseBody = given().delete("https://reqres.in/api/users/2");
        Assert.assertEquals(responseBody.statusCode(), 204);
        System.out.println("El codigo de respuesta es: " + responseBody.getStatusCode() + " se asegura la eliminación del usuario");
    }

    @Test
    public void DeleteTest_02() {
        String pathDelete = "/2";
        Response responseBody = given().delete(url+pathUser+pathDelete);
        Assert.assertEquals(responseBody.statusCode(), 204);
        System.out.println("El codigo de respuesta es: " + responseBody.getStatusCode() + " se asegura la eliminación del usuario");
    }

}
