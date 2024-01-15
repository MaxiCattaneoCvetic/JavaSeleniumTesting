package com.example.SeleniumTesting.patronPOMtest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.example.SeleniumTesting.patronPOM.LoginPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import reportes.ExtentFactory;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;

public class LoginTest {

    public WebDriver driver;
    public WebDriverWait wait;
    final String URL = "http://digital-booking.ctd.academy/login";

    //Indicamos donde se creara el reeporte
    static ExtentSparkReporter info = new ExtentSparkReporter("target/Reporte_login.html");

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
        LoginPage loginPage  = new LoginPage(driver,wait);
        loginPage.getUrl("http://digital-booking.ctd.academy/login");
    }
    @AfterEach
    public void endTest() {
        LoginPage loginPage  = new LoginPage(driver,wait);
        loginPage.close();

    }

    @AfterAll
    public static void saveReport() {
        // con esto creamos el documento como tal en la ruta
        extent.flush();
    }

    @Test
    @Tag("LOGIN")
    public void login_succesfull() throws InterruptedException {
        //representar un caso de prueba en el informe de pruebas
        ExtentTest test = extent.createTest("Test de login");
        test.log(Status.INFO,"Comienzo de test de LOGIN");
        String email = "cveticmaxi97@gmail.com";
        String password ="123456";


        LoginPage loginPage  = new LoginPage(driver,wait);

        loginPage.writeEmail(email);
        loginPage.writePassword(password);
        test.log(Status.PASS,"Se cargaron los datos validos de login");

        loginPage.clickLogin();



        Assertions.assertEquals(loginPage.saludo(),"Hola,");
        Assertions.assertEquals(loginPage.name(),"maxi cattaneo cvetic");
        test.log(Status.PASS,"Se valida el login exitoso");


    }

    // casos fallidos
    @Test
    @Tag("LOGIN")
    public void login_datos_vacios() throws InterruptedException {
        //representar un caso de prueba en el informe de pruebas
        ExtentTest test = extent.createTest("Intentamos logearnos sin agregar ningun dato- Fallido");
        test.log(Status.INFO,"Comienzo de test de LOGIN");
        String email = "";
        String password ="";


        LoginPage loginPage  = new LoginPage(driver,wait);

        loginPage.writeEmail(email);
        loginPage.writePassword(password);
        test.log(Status.PASS,"No se agregan datos del login");

        loginPage.clickLogin();



        Assertions.assertEquals(loginPage.correoObligatorio(),"Este campo es obligatorio");
        Assertions.assertEquals(loginPage.passwordObligatorio(),"Este campo es obligatorio");
        test.log(Status.PASS,"Se valida el mensaje de campos obligatorios");
    }
    @Test
    @Tag("LOGIN")
    public void login_correo_invalido() throws InterruptedException {
        //representar un caso de prueba en el informe de pruebas
        ExtentTest test = extent.createTest("Intentamos logearnos sin agregar ningun dato- Fallido");
        test.log(Status.INFO,"Comienzo de test de LOGIN");
        String email = "holahola";
        String password ="";


        LoginPage loginPage  = new LoginPage(driver,wait);

        loginPage.writeEmail(email);
        loginPage.writePassword(password);
        test.log(Status.PASS,"Se agrega un correo invalido");

        loginPage.clickLogin();
        loginPage.emailInvalid();
        test.log(Status.PASS,"Se valida el mensaje de correo invalido");
    }
    @Test
    @Tag("LOGIN")
    public void password_short() throws InterruptedException {
        //representar un caso de prueba en el informe de pruebas
        ExtentTest test = extent.createTest("Intentamos logearnos con una contraseña menor a 6 caracteres- Fallido");
        test.log(Status.INFO,"Comienzo de test de LOGIN");
        String email = "cveticmaxi97@gmail.com";
        String password ="123";


        LoginPage loginPage  = new LoginPage(driver,wait);

        loginPage.writeEmail(email);
        loginPage.writePassword(password);
        test.log(Status.PASS,"Se agrega los datos con una  pass menor a 6 caract");

        loginPage.clickLogin();
        loginPage.passwordShort();
        test.log(Status.PASS,"Se valida el mensaje de correo invalido");
    }

    @Test
    @Tag("LOGIN")
    public void credentials_invalid() throws InterruptedException {
        //representar un caso de prueba en el informe de pruebas
        ExtentTest test = extent.createTest("Intentamos logearnos con una credenciales invalidas- Fallido");
        test.log(Status.INFO,"Comienzo de test de LOGIN");
        String email = "cveticmaxi97@gmail.com";
        String password ="1234578451";


        LoginPage loginPage  = new LoginPage(driver,wait);

        loginPage.writeEmail(email);
        loginPage.writePassword(password);
        test.log(Status.PASS,"Se intenta logear con credenciales invalidas");

        loginPage.clickLogin();
        loginPage.passwordInvalid();
        test.log(Status.PASS,"Se valida que el usuario no tiene acceso con credencial invalida");
    }


}
