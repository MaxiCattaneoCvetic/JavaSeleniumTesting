package com.example.SeleniumTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasePage {

    //creamos esta clase para que sea nuestra superclase
    public WebDriver driver;


    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public BasePage() {
    }


    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public void getUrl(String url) {
        driver.get(url);
    }

    public void close() {
        driver.quit();
    }

    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    public void sendText(String text, By locator) {
        //recibe dos parametros el texto y el localizador
        this.findElement(locator).clear(); // ---> Limpiamos por si hay algo escrito
        this.findElement(locator).sendKeys(text); // -- > Enviamos el texto
    }

    public void sendKey(CharSequence key, By locator) {
        this.findElement(locator).sendKeys(key);

    }

    public void click (By locator) {
        this.findElement(locator).click();
    }

    public String getText(By locator) {
        return this.findElement(locator).getText();

    }









}
