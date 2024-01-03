package com.example.SeleniumTesting;

import org.openqa.selenium.*;

public class SearchPage extends BasePage {


    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public SearchPage() {
    }

    private By searchBox = By.id("ciudad");
    private By searchButton = By.id("btn-buscador");
    private By searchOk = By.className("categoria");

    public void searchWrite(String ciudad) {
        this.sendText(ciudad,searchBox);
        this.sendKey(Keys.ENTER, searchBox);

    }

    public void clickBuscar() {
        this.click(searchButton);
    }

    public String searchResult() {
        String response = this.getText(searchOk);
        System.out.println("Resultado de la busqueda: " + response);
        return response;

    }





}
