package com.example.SeleniumTesting.testGoogle;

import com.example.SeleniumTesting.SearchPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SearchTest {
    public WebDriver driver;

    @Test
    public void searchSuccessful() throws InterruptedException {
        driver = new ChromeDriver();
        // Modificamos ciertos parametros de nuestro navegador
        driver.manage().window().maximize(); // ---> Maximizamos la web en donde estamos testeando

        driver.get("http://testing.ctd.academy/");

        // agregamos pausas
        Thread.sleep(1000);

        //buscamos el elemento de la barra de busqueda por su ID
        WebElement searchBox = driver.findElement(By.id("ciudad"));

        //Escribimos el elemento que queremos buscar
        searchBox.sendKeys("Punta del este");
        searchBox.sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        WebElement searchButton = driver.findElement(By.id("btn-buscador"));
        searchButton.click();
        Thread.sleep(1000);

        //Copiamos el xpath cuando inspeccionamos en el navegador, debemos verificar que el xpath sea unico
        //No recomandamos utilizar fullXpath
        WebElement searchOk = driver.findElement(By.xpath("//*[@id=\"68\"]/div[2]/div/div[1]"));
        String busquedaCorrecta = searchOk.getText();
        System.out.println(busquedaCorrecta);

        driver.quit();
    }


    @Test
    public void searchSuccessful_Grecia() throws InterruptedException {
        String url = "http://testing.ctd.academy/";
        String ciudad = "Paros";

        SearchPage searchPage  = new SearchPage(driver);
        searchPage.setup();
        searchPage.getUrl(url);

        Thread.sleep(1000);

        searchPage.searchWrite(ciudad);
        Thread.sleep(1000);

        searchPage.clickBuscar();

        Thread.sleep(1000);

        searchPage.searchResult();


        searchPage.close();




    }
}
