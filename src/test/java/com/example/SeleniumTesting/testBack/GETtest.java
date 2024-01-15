package com.example.SeleniumTesting.testBack;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class GETtest extends BaseTest {

    // Seleccionamos donde queremos solicitar el GET
    //RESTassure & TESTNG


    @Test
    public void get_test01() {
        String path = "users?page=2";
        Response resGet = RestAssured.get(url+pathUser+path);
        System.out.println(resGet.getBody().asString());
        System.out.println(resGet.statusCode());
        System.out.println(resGet.getHeader("Date"));
        System.out.println(resGet.getTime());
    }

    @Test //Agregamos validaciones
    public void get_test02() {
        Response resGet = RestAssured.get("https://reqres.in/api/users?page=2");

        //Codigo de respuesta
        int statusCode = resGet.getStatusCode();

        // Guardamos el json de la respuesta
        JsonPath body = resGet.jsonPath();

        //Nos manejamos dentro del array que viene en el json
        String nombre_0 = body.getString("data.first_name[0]");
        String email_0 = body.getString("data.email[0]");

        String nombre_2 = body.getString("data.first_name[2]");
        String email_2 = body.getString("data.email[2]");


        // usamos los Assert de TESTNG
        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals(nombre_0, "Michael");
        System.out.println("El nombre del primer registro es: " + nombre_0);
        Assert.assertEquals(email_0, "michael.lawson@reqres.in");
        System.out.println("El correo del primer registro es: " + email_0);

        System.out.println("-------------- Codigo de status: " + statusCode + "---------------------------");

        Assert.assertEquals(nombre_2, "Tobias");
        System.out.println("El nombre del tercer registro es: " + nombre_2);
        Assert.assertEquals(email_2, "tobias.funke@reqres.in");
        System.out.println("El correo del tercer registro es: " + email_2);

    }

    @Test
    // podemos utilizar las palabras claves de BDD
    public void get_test03() {
        // dada la web, luego de ingresar deberia tener status 200
        given().get("https://reqres.in/api/users?page=2").then().statusCode(200).log().body();


    }
}
