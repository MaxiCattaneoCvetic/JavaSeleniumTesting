package com.example.SeleniumTesting.patronPOM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class BasePage {

    //creamos esta clase para que sea nuestra superclase
    public WebDriver driver;

    public WebDriverWait wait;

    protected BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofMillis(3000)); // -> No es que hacemos una espera de 3s, sino que esperamos hasta 3 segundos
        // En el caso de que elocalizador se encuentre antes no hara la espera
    }

    protected BasePage() {
    }


    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public void getUrl(String url) {
        driver.navigate().to(url);
    }

    public void close() {
        driver.quit();
    }

    protected WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    protected void sendText(String text, By locator) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator)); // -> Esperamos a que el localizador este presente
        //recibe dos parametros el texto y el localizador
        this.findElement(locator).clear(); // ---> Limpiamos por si hay algo escrito
        this.findElement(locator).sendKeys(text); // -- > Enviamos el texto
    }

    protected void sendKey(CharSequence key, By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator)); // -> Esperamos a que el localizador este presente

        this.findElement(locator).sendKeys(key);

    }

    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)); // -> Esperamos a que el elemento se clickee
        this.findElement(locator).click();
    }

    protected String getText(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator)); // -> Esperamos a que el localizador este presente
        return this.findElement(locator).getText();

    }


}
