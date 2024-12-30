package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutComplete {
    WebDriver driver;

    public CheckoutComplete(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement messageComplete() {
        return  driver.findElement(By.xpath("//span[@class='title']"));
    }
}
