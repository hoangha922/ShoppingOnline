package action;

import org.openqa.selenium.WebDriver;
import ui.CartPageUI;
import ui.InventoryPageUI;
import ui.LoginPageUI;

public class AddProductToCart {
    WebDriver driver;
    LoginPageUI loginPageUI;
    CartPageUI cartPageUI;
    InventoryPageUI inventoryPageUI;

    public AddProductToCart(WebDriver driver) {
        this.driver = driver;
        loginPageUI = new LoginPageUI(driver);
        inventoryPageUI = new InventoryPageUI(driver);
        cartPageUI = new CartPageUI(driver);
    }
    public void addProductToCart() {
        inventoryPageUI.buttonAddToCart1().click();
        inventoryPageUI.buttonAddToCart2().click();
        inventoryPageUI.buttonAddToCart3().click();
        inventoryPageUI.shoppingCart().click();
        cartPageUI.buttonCheckout().click();
    }
}
