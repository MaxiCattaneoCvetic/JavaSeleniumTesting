package com.example.SeleniumTesting.patronPOM;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends BasePage {

    private By searchBox = By.id("ciudad");
    private By searchButton = By.id("btn-buscador");
    private By searchOk = By.className("categoria");

    public SearchPage(WebDriver driver, WebDriverWait wait) {
        super(driver,wait);
    }

    public SearchPage() {
    }



    public void searchWrite(String ciudad) throws InterruptedException {
        this.sendText(ciudad,searchBox);
        this.sendKey(Keys.ENTER, searchBox);

    }

    public void clickBuscar() throws InterruptedException {
        this.click(searchButton);

    }

    public String searchResult() throws InterruptedException {
        String response = this.getText(searchOk);
        System.out.println("Resultado de la busqueda: " + response);
        return response;

    }





}
