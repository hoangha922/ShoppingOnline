package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InventoryPageUI {
    WebDriver driver;

    public InventoryPageUI(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement buttonAddToCart1() {
        return driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']"));
    }

    public WebElement buttonAddToCart2() {
        return driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-bike-light']"));
    }

    public WebElement buttonAddToCart3() {
        return driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-bolt-t-shirt']"));
    }

    public WebElement quantityCart() {
        return  driver.findElement(By.xpath("//span[@class='shopping_cart_badge']"));
    }

    public WebElement shoppingCart() {
        return  driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
    }

    public WebElement nameProduct1() {
        return driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']"));
    }

    public WebElement nameProduct2() {
        return driver.findElement(By.xpath("//div[text()='Sauce Labs Bike Light']"));
    }

    public WebElement nameProduct3() {
        return driver.findElement(By.xpath("//div[text()='Sauce Labs Bolt T-Shirt']"));
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

    public WebElement removeProduct2() {
        return driver.findElement(By.id("remove-sauce-labs-bike-light"));
    }




}
