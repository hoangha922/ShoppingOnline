package action;

import org.openqa.selenium.WebDriver;
import ui.*;

public class InputInformation {
    WebDriver driver;
    LoginPageUI loginPageUI;
    CartPageUI cartPageUI;
    InventoryPageUI inventoryPageUI;
    CheckoutStepOnePageUI checkoutStepOnePageUI;

    public InputInformation(WebDriver driver) {
        this.driver = driver;
        loginPageUI = new LoginPageUI(driver);
        inventoryPageUI = new InventoryPageUI(driver);
        cartPageUI = new CartPageUI(driver);
        checkoutStepOnePageUI = new CheckoutStepOnePageUI(driver);
    }
    public void inputInformation() {
        checkoutStepOnePageUI.inputFirstName().sendKeys("Hà");
        checkoutStepOnePageUI.inputLastName().sendKeys("Hoàng Thái");
        checkoutStepOnePageUI.inputCode().sendKeys("A1234");
        checkoutStepOnePageUI.buttonContinue().click();
    }
}
