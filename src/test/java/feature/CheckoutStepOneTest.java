package feature;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import ui.CartPageUI;
import ui.CheckoutStepOnePageUI;
import ui.InventoryPageUI;
import ui.LoginPageUI;

import java.time.Duration;

public class CheckoutStepOneTest {
    WebDriver driver;
    WebDriverWait wait;
    public LoginPageUI loginPageUI;
    public InventoryPageUI inventoryPageUI;
    public CartPageUI cartPageUI;
    public CheckoutStepOnePageUI checkoutStepOnePageUI;

    public void login() {
        loginPageUI.inputUserName().sendKeys("standard_user");
        loginPageUI.inputPassWord().sendKeys("secret_sauce");
        loginPageUI.buttonLogin().click();
    }
    public void addProductToCart() {
        inventoryPageUI.buttonAddToCart1().click();
        inventoryPageUI.buttonAddToCart2().click();
        inventoryPageUI.buttonAddToCart3().click();
        inventoryPageUI.shoppingCart().click();
        cartPageUI.buttonCheckout().click();
    }

    @BeforeMethod
    public void setURL() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.get("https://www.saucedemo.com/");

        loginPageUI = new LoginPageUI(driver);
        inventoryPageUI = new InventoryPageUI(driver);
        cartPageUI = new CartPageUI(driver);
        checkoutStepOnePageUI = new CheckoutStepOnePageUI(driver);

        login();
        addProductToCart();

    }

    @Test
    public void inputInformationSuccessful() {
        checkoutStepOnePageUI.inputFirstName().sendKeys("Hà");
        checkoutStepOnePageUI.inputLastName().sendKeys("Hoàng Thái");
        checkoutStepOnePageUI.inputCode().sendKeys("A1234");
        checkoutStepOnePageUI.buttonContinue().click();
    }

    @Test
    public void inputFirstNameNull() {
        checkoutStepOnePageUI.inputFirstName().sendKeys("");
        checkoutStepOnePageUI.inputLastName().sendKeys("Hoàng Thái");
        checkoutStepOnePageUI.inputCode().sendKeys("A1234");
        checkoutStepOnePageUI.buttonContinue().click();
        String error = checkoutStepOnePageUI.errorMessage().getText();
        Assert.assertEquals(error, "Error: First Name is required");

    }

    @Test
    public void inputLastNameNull() {
        checkoutStepOnePageUI.inputFirstName().sendKeys("Hà");
        checkoutStepOnePageUI.inputLastName().sendKeys("");
        checkoutStepOnePageUI.inputCode().sendKeys("A1234");
        checkoutStepOnePageUI.buttonContinue().click();
        String error = checkoutStepOnePageUI.errorMessage().getText();
        Assert.assertEquals(error, "Error: Last Name is required");
    }

    @Test
    public void inputZipCodeNull() {
        checkoutStepOnePageUI.inputFirstName().sendKeys("Hà");
        checkoutStepOnePageUI.inputLastName().sendKeys("Hoàng Thái");
        checkoutStepOnePageUI.inputCode().sendKeys("");
        checkoutStepOnePageUI.buttonContinue().click();
        String error = checkoutStepOnePageUI.errorMessage().getText();
        Assert.assertEquals(error, "Error: Postal Code is required");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
