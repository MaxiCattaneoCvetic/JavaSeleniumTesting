package com.example.SeleniumTesting.patronPOM;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage extends BasePage {

    private By hello = By.className("txt-hola");
    private By name = By.className("txt-nombre");
    private By email = By.id("email");
    private By password = By.id("password");
    private By loginBtn = By.className("btn-primario");
    private By emailReq= By.xpath("//*[@id=\"root\"]/main/div/form/div[1]/small");
    private By passwordReq= By.xpath("//*[@id=\"root\"]/main/div/form/div[2]/small");
    private By emailInv= By.xpath("//*[@id=\"root\"]/main/div/form/div[1]/small");
    private By passwordShort= By.xpath("//*[@id=\"root\"]/main/div/form/div[2]/small");
    private By invalidPassword= By.xpath("//*[@id=\"root\"]/main/div/form/p[1]");





    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver,wait);
    }



    public void writeEmail(String correo) throws InterruptedException {
        this.sendText(correo,email);
    }
    public void writePassword(String pass) throws InterruptedException {
        this.sendText(pass,password);
    }


    public void clickLogin() throws InterruptedException {
        this.click(loginBtn);
    }

    public String correoObligatorio() throws InterruptedException {
        System.out.println("Mensaje de error: " + this.getText(emailReq));
        return  this.getText(emailReq);
    }

    public String passwordObligatorio() throws InterruptedException {
        System.out.println("Mensaje de error: " + this.getText(passwordReq));
        return  this.getText(passwordReq);
    }

    public String emailInvalid() throws InterruptedException {
        System.out.println("Mensaje de error: " + this.getText(emailInv));
        return  this.getText(emailInv);
    }
    public String passwordInvalid() throws InterruptedException {
        System.out.println("Mensaje de error: " + this.getText(invalidPassword));
        return  this.getText(invalidPassword);
    }
    public String passwordShort() throws InterruptedException {
        System.out.println("Mensaje de error: " + this.getText(passwordShort));
        return  this.getText(passwordShort);
    }

    public String saludo() throws InterruptedException {
        System.out.println("Mensaje de SALUDO: " + this.getText(hello));
        return  this.getText(hello);
    }

    public String name() throws InterruptedException {
        System.out.println("Nombre en pantalla: " + this.getText(name));
        return  this.getText(name);
    }






}
