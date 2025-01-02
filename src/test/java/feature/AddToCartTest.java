package feature;

import action.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import ui.CartPageUI;
import ui.InventoryPageUI;
import ui.LoginPageUI;

import java.time.Duration;
import java.util.List;

public class AddToCartTest {
    WebDriver driver;
    WebDriverWait wait;
    public LoginPageUI loginPageUI;
    public InventoryPageUI inventoryPageUI;
    public CartPageUI cartPageUI;
    public Login login;

    @BeforeMethod
    public void setURL() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.get("https://www.saucedemo.com/");

        loginPageUI = new LoginPageUI(driver);
        inventoryPageUI = new InventoryPageUI(driver);
        cartPageUI = new CartPageUI(driver);
        login = new Login(driver);

        login.login();
    }

    @Test
    public void add2ProductsSuccessful() {

        inventoryPageUI.buttonAddToCart1().click();
        inventoryPageUI.buttonAddToCart2().click();

//      Kiểm tra số lượng sản phẩm ở icon giỏ hàng
        String actualQuantity = inventoryPageUI.quantityCart().getText();
        Assert.assertEquals(actualQuantity, "2");

        inventoryPageUI.shoppingCart().click();

//      Kiểm tra tên sản phẩm 1 trong giỏ hàng
        String nameProduct1Inventory = inventoryPageUI.nameProduct1().getText();
        String nameProduct1Cart = cartPageUI.nameProduct1().getText();
        Assert.assertEquals(nameProduct1Cart, nameProduct1Inventory);

//      Kiểm tra số lượng sản phẩm 1 trong giỏ hàng
        String quantityProduct1Cart = cartPageUI.quantityProduct1().getText();
        Assert.assertEquals(quantityProduct1Cart, "1");

//        Kiểm tra giá của sản phẩm 1 trong giỏ hàng
        String priceProduct1Cart = cartPageUI.priceProduct1().getText();
        String priceProduct1Inventory = inventoryPageUI.priceProduct1().getText();
        Assert.assertEquals(priceProduct1Cart, priceProduct1Inventory);

//      Kiểm tra tên sản phẩm 2 trong giỏ hàng
        String nameProduct2Inventory = inventoryPageUI.nameProduct2().getText();
        String nameProduct2Cart = cartPageUI.nameProduct2().getText();
        Assert.assertEquals(nameProduct2Cart, nameProduct2Inventory);

//      Kiểm tra số lượng sản phẩm 2 trong giỏ hàng
        String quantityProduct2Cart = cartPageUI.quantityProduct2().getText();
        Assert.assertEquals(quantityProduct2Cart, "1");

//        Kiểm tra giá của sản phẩm 2 trong giỏ hàng
        String priceProduct2Cart = cartPageUI.priceProduct2().getText();
        String priceProduct2Inventory = inventoryPageUI.priceProduct2().getText();
        Assert.assertEquals(priceProduct2Cart, priceProduct2Inventory);

    }

    @Test
    public void remove1Product() {
//        Thêm 2 sản phầm vào giỏ hàng
        inventoryPageUI.buttonAddToCart1().click();
        inventoryPageUI.buttonAddToCart2().click();

//      Xóa sản phẩm 2 khỏi giỏ hàng
        inventoryPageUI.removeProduct2().click();

//      Kiểm tra số lượng sản phẩm ở icon giỏ hàng
        String actualQuantity = inventoryPageUI.quantityCart().getText();
        Assert.assertEquals(actualQuantity, "1");

        inventoryPageUI.shoppingCart().click();

//      Kiểm tra tên sản phẩm 1 trong giỏ hàng
        String nameProduct1Inventory = inventoryPageUI.nameProduct1().getText();
        String nameProduct1Cart = cartPageUI.nameProduct1().getText();
        Assert.assertEquals(nameProduct1Cart, nameProduct1Inventory);

//      Kiểm tra số lượng sản phẩm 1 trong giỏ hàng
        String quantityProduct1Cart = cartPageUI.quantityProduct1().getText();
        Assert.assertEquals(quantityProduct1Cart, "1");

//        Kiểm tra giá của sản phẩm 1 trong giỏ hàng
        String priceProduct1Cart = cartPageUI.priceProduct1().getText();
        String priceProduct1Inventory = inventoryPageUI.priceProduct1().getText();
        Assert.assertEquals(priceProduct1Cart, priceProduct1Inventory);

//      Kiểm tra tên sản phẩm 2 có trong giỏ hàng hay không
        List<WebElement> products = driver.findElements(By.xpath("//*[text()='Sauce Labs Bike Light']"));
        Assert.assertTrue(products.isEmpty(), "The product 'Sauce Labs Bike Light' is unexpectedly found.");

    }
    @AfterMethod
    public void tearDown() {
        driver.quit();

    }

}
