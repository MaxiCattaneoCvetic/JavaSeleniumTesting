package com.example.SeleniumTesting.patronPOMtest;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestGoogleAuto {
    // instanciamos el driver de webDriver

    public  WebDriver webDriver;

    @Test
    public  void test_1(){
        //inicializamos el driver de chrome
        webDriver = new ChromeDriver();
        webDriver.get("https://google.com");
        webDriver.getTitle(); // queremos obtener que este en google

        //creamos una variable y depositamos un elemento puntual de la pantalla
        WebElement searchBox = webDriver.findElement(By.name("q")); //q es el nombre de la barra de busqueda en google
        WebElement searchButton = webDriver.findElement(By.name("btnK")); //q es el nombre de la barra de busqueda en google


        searchBox.sendKeys("Selenium");
        /*searchButton.click();*/
        searchBox.sendKeys(Keys.ENTER);

        //debemos volver a seleccionar el searchbox
        searchBox = webDriver.findElement(By.name("q"));
        String value = searchBox.getAttribute("value");
        System.out.println(value);

        webDriver.quit();

    }

    public  void main(String[] args) {


    }
}
