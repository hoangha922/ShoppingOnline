package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckOutStepTwoPageUI {
    WebDriver driver;

    public CheckOutStepTwoPageUI(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement itemTotal() {
        return  driver.findElement(By.xpath("//div[@class='summary_subtotal_label']"));
    }

    public WebElement tax() {
        return driver.findElement(By.xpath("//div[@class='summary_tax_label']"));
    }

    public WebElement total() {
        return  driver.findElement(By.xpath("//div[@class='summary_total_label']"));
    }

    public WebElement buttonFinish() {
        return  driver.findElement(By.id("finish"));
    }

    public WebElement nameProduct1() {
        return  driver.findElement(By.xpath("//a[@id='item_4_title_link']"));
    }

    public WebElement nameProduct2() {
        return  driver.findElement(By.xpath("//a[@id='item_0_title_link']"));
    }

    public WebElement nameProduct3() {
        return  driver.findElement(By.xpath("//a[@id='item_1_title_link']"));
    }

    public WebElement quantityProduct1() {
        return  driver.findElement(By.xpath("(//div[@class='cart_quantity'])[1]"));
    }

    public WebElement quantityProduct2() {
        return  driver.findElement(By.xpath("(//div[@class='cart_quantity'])[2]"));
    }

    public WebElement quantityProduct3() {
        return  driver.findElement(By.xpath("(//div[@class='cart_quantity'])[3]"));
    }

    public WebElement priceProduct1() {
        return driver.findElement(By.xpath("(//div[@class='inventory_item_price'])[1]"));
    }

    public WebElement priceProduct2() {
        return driver.findElement(By.xpath("(//div[@class='inventory_item_price'])[2]"));
    }

    public WebElement priceProduct3() {
        return driver.findElement(By.xpath("(//div[@class='inventory_item_price'])[3]"));
    }


}
