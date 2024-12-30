package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPageUI {
    WebDriver driver;
    //Constructor
    public LoginPageUI(WebDriver driver) {
        this.driver = driver;
    }
    public WebElement inputUserName() {
        return driver.findElement(By.xpath("//input[@id='user-name']"));
    }

    public WebElement inputPassWord() {
        return  driver.findElement(By.xpath("//input[@id='password']"));
    }

    public WebElement buttonLogin() {
        return  driver.findElement(By.id("login-button"));
    }
}
