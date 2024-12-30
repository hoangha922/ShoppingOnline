package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutStepOnePageUI {
    WebDriver driver;

    public CheckoutStepOnePageUI(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement inputFirstName() {
        return  driver.findElement(By.xpath("//input[@id='first-name']"));
    }

    public WebElement inputLastName() {
        return  driver.findElement(By.id("last-name"));
    }

    public WebElement inputCode() {
        return  driver.findElement(By.id("postal-code"));
    }

    public WebElement buttonContinue() {
        return  driver.findElement(By.id("continue"));
    }

    public WebElement errorMessage() {
        return driver.findElement(By.xpath("//h3[@data-test='error']"));
    }

}
