package feature;

import action.AddProductToCart;
import action.InputInformation;
import action.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import ui.*;

import java.time.Duration;

public class CheckoutStepTwoTest {
    WebDriver driver;
    WebDriverWait wait;
    public LoginPageUI loginPageUI;
    public InventoryPageUI inventoryPageUI;
    public CartPageUI cartPageUI;
    public CheckoutStepOnePageUI checkoutStepOnePageUI;
    public CheckOutStepTwoPageUI checkOutStepTwoPageUI;
    public Login login;
    public AddProductToCart addProductToCart;
    public InputInformation inputInformation;

//   Chuyển đổi giá sản phẩm từ String thành Double
    private double convertToDouble(String priceString) {
        // Loại bỏ các ký tự không phải là số và dấu chấm
        String numberString = priceString.replaceAll("[^0-9\\.]", "");
        return Double.parseDouble(numberString);  // Chuyển chuỗi thành double
    }
//    Tính tổng giá 3 sản phẩm
    private double calculateTotalPrice() {
        // Sử dụng hàm convertToDouble để chuyển các giá trị sản phẩm thành double
        double numberPriceProduct1 = convertToDouble(checkOutStepTwoPageUI.priceProduct1().getText());
        double numberPriceProduct2 = convertToDouble(checkOutStepTwoPageUI.priceProduct2().getText());
        double numberPriceProduct3 = convertToDouble(checkOutStepTwoPageUI.priceProduct3().getText());

        return numberPriceProduct1 + numberPriceProduct2 + numberPriceProduct3;
    }
//    Tính thuế
    private double calculateTax() {
        // Sử dụng hàm convertToDouble để chuyển các giá trị sản phẩm thành double
        double numberPriceProduct1 = convertToDouble(checkOutStepTwoPageUI.priceProduct1().getText());
        double numberPriceProduct2 = convertToDouble(checkOutStepTwoPageUI.priceProduct2().getText());
        double numberPriceProduct3 = convertToDouble(checkOutStepTwoPageUI.priceProduct3().getText());

        return (numberPriceProduct1 + numberPriceProduct2 + numberPriceProduct3) / 100 * 8;
    }

    @BeforeTest
    public void setURL() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.get("https://www.saucedemo.com/");

        loginPageUI = new LoginPageUI(driver);
        inventoryPageUI = new InventoryPageUI(driver);
        cartPageUI = new CartPageUI(driver);
        checkoutStepOnePageUI = new CheckoutStepOnePageUI(driver);
        checkOutStepTwoPageUI = new CheckOutStepTwoPageUI(driver);
        login = new Login(driver);
        addProductToCart = new AddProductToCart(driver);
        inputInformation = new InputInformation(driver);

        login.login();
        addProductToCart.addProductToCart();
        inputInformation.inputInformation();
    }

    @Test
    public void checkInformationProduct1() {
//        Kiểm tra tên sản phẩm 1
        String nameProduct1 = checkOutStepTwoPageUI.nameProduct1().getText();
        String nameProduct1Inventory = inventoryPageUI.nameProduct1().getText();
        Assert.assertEquals(nameProduct1, nameProduct1Inventory);

//        Kiểm tra số lượng sản phẩm 1
        String quantityProduct1 = checkOutStepTwoPageUI.quantityProduct1().getText();
        Assert.assertEquals(quantityProduct1, "1");

//        Kiểm tra giá sản phẩm 1
        String priceProduct1 = checkOutStepTwoPageUI.priceProduct1().getText();
        String priceProduct1Inventory = inventoryPageUI.priceProduct1().getText();
        Assert.assertEquals(priceProduct1, priceProduct1Inventory);
    }

    @Test
    public void checkInformationProduct2() {
        String nameProduct2 = checkOutStepTwoPageUI.nameProduct2().getText();
        String nameProduct2Inventory = inventoryPageUI.nameProduct2().getText();
        Assert.assertEquals(nameProduct2, nameProduct2Inventory);

        String quantityProduct2 = checkOutStepTwoPageUI.quantityProduct2().getText();
        Assert.assertEquals(quantityProduct2, "1");

        String priceProduct2 = checkOutStepTwoPageUI.priceProduct2().getText();
        String priceProduct2Inventory = inventoryPageUI.priceProduct2().getText();
        Assert.assertEquals(priceProduct2, priceProduct2Inventory);
    }

    @Test
    public void checkInformationProduct3() {
        String nameProduct3 = checkOutStepTwoPageUI.nameProduct3().getText();
        String nameProduct3Inventory = inventoryPageUI.nameProduct3().getText();
        Assert.assertEquals(nameProduct3, nameProduct3Inventory);

        String quantityProduct3 = checkOutStepTwoPageUI.quantityProduct3().getText();
        Assert.assertEquals(quantityProduct3, "1");

        String priceProduct3 = checkOutStepTwoPageUI.priceProduct3().getText();
        String priceProduct3Inventory = inventoryPageUI.priceProduct3().getText();
        Assert.assertEquals(priceProduct3, priceProduct3Inventory);
    }

    @Test
    public void checkPriceTotal() {
//        Kiểm tra tổng tiền 3 sản phầm trước thuế
        String itemTotal = checkOutStepTwoPageUI.itemTotal().getText();
        double numberItemTotal = convertToDouble(itemTotal);

        double total3Products = calculateTotalPrice();

        Assert.assertEquals(numberItemTotal, total3Products);
    }

    @Test
    public void checkTax() {
//        Kiểm tra tiền thuế
        String tax = checkOutStepTwoPageUI.tax().getText();
        double numberTax = convertToDouble(tax);

        double tax3Products = calculateTax();

        Assert.assertEquals(numberTax, tax3Products, 0.01);
    }

    @Test
    public void checkTotal() {
//        Kiểm tra tổng tiền 3 sản phầm
        String total = checkOutStepTwoPageUI.total().getText();
        double numberTotal = convertToDouble(total);

        double totalAndTax = calculateTotalPrice() + calculateTax();

        Assert.assertEquals(numberTotal, totalAndTax, 0.01);

    }

    @Test
    public void finish() {
        checkOutStepTwoPageUI.buttonFinish().click();
        WebElement messageComplete = driver.findElement(By.xpath("//span[@class='title']"));
        String actualMessage = messageComplete.getText();
        Assert.assertEquals(actualMessage, "Checkout: Complete!");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}
