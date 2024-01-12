package com.example.SeleniumTesting.dhTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.example.SeleniumTesting.SearchPage;
import org.junit.jupiter.api.*;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.support.ui.WebDriverWait;
import reportes.ExtentFactory;

import java.io.IOException;

import java.time.Duration;
import java.util.Collections;

public class SearchTest {
    public WebDriver driver;
    public WebDriverWait wait;
    final String URL = "http://digital-booking.ctd.academy";

    //Indicamos donde se creara el reeporte
    static ExtentSparkReporter info = new ExtentSparkReporter("target/Reporte_search.html");

    static ExtentReports extent;


    @BeforeAll
    public static void createReports() {
        // las variables y metodos denben ser estaticas ya que pertenecen a la clase general
        // Creamos el archivo de reporte
        extent= ExtentFactory.getInsance(); //creamos la instancia
        extent.attachReporter(info); // generamos el reporte
    }

    @BeforeEach // aca vamos a instanciar todo lo que sea necesario que se ejecute antes para este caso de prueba
    public void preconditions() throws InterruptedException, IOException {

        // Configurar opciones de Chrome para evitar redirección a HTTPS
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--headless");  // Si prefieres ejecutar en modo sin cabeza
        chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        chromeOptions.setExperimentalOption("useAutomationExtension", false);

        driver = new ChromeDriver(chromeOptions);


        wait = new WebDriverWait(driver, Duration.ofMillis(3000));
        SearchPage searchPage  = new SearchPage(driver,wait);
        searchPage.getUrl("http://digital-booking.ctd.academy");


    }

    @AfterEach
    public void endTest() {
        SearchPage searchPage  = new SearchPage(driver,wait);
        searchPage.close();

    }

    @AfterAll
    public static void saveReport() {
        // con esto creamos el documento como tal en la ruta
        extent.flush();
    }
/*    @Test
    public void searchSuccessful() throws InterruptedException {
*//*        driver = new ChromeDriver();
        // Modificamos ciertos parametros de nuestro navegador
        driver.manage().window().maximize(); // ---> Maximizamos la web en donde estamos testeando

        driver.get("http://testing.ctd.academy/");

        // agregamos pausas
        Thread.sleep(1000);*//*

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

    }*/


    //"Patron POM- Page Object"
    @Test
    @Tag("BUSQUEDA")
    public void searchSuccessful_Grecia() throws InterruptedException {
        //representar un caso de prueba en el informe de pruebas
        ExtentTest test = extent.createTest("Busqueda exitosa en Grecia");
        test.log(Status.INFO,"Comienzo de test de busqueda Grecia");

        String ciudad = "Paros";
        SearchPage searchPage  = new SearchPage(driver,wait);
        searchPage.searchWrite(ciudad);
        searchPage.clickBuscar();
        test.log(Status.PASS,"Se realizo la busqueda Exitosa de Grecia");

        searchPage.searchResult();

    }
}
