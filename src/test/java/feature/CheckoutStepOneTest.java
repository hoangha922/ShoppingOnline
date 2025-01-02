package feature;

import action.AddProductToCart;
import action.Login;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import ui.CartPageUI;
import ui.CheckoutStepOnePageUI;
import ui.InventoryPageUI;
import ui.LoginPageUI;
import utils.ExcelUtils;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class CheckoutStepOneTest {
    WebDriver driver;
    WebDriverWait wait;
    public LoginPageUI loginPageUI;
    public InventoryPageUI inventoryPageUI;
    public CartPageUI cartPageUI;
    public CheckoutStepOnePageUI checkoutStepOnePageUI;
    public Login login;
    public AddProductToCart addProductToCart;

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
        login = new Login(driver);
        addProductToCart = new AddProductToCart(driver);

        login.login();
        addProductToCart.addProductToCart();
    }

    @DataProvider(name = "demoData")
    public Object[][] provideTestData() {
        String excelFilePath = "inputInformation.xlsx";
        String sheetName = "Sheet1";

        try {
            // Đọc dữ liệu từ file Excel
            List<Map<String, String>> excelData = ExcelUtils.readExcelData(excelFilePath, sheetName);

            // Chuyển đổi danh sách Map thành mảng 2 chiều
            Object[][] data = new Object[excelData.size()][1];
            for (int i = 0; i < excelData.size(); i++) {
                data[i][0] = excelData.get(i);
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi đọc tệp Excel: " + e.getMessage());
        }
    }

    public void fillForm(Map<String, String> rowData) {
        try {
            checkoutStepOnePageUI.inputFirstName().sendKeys(rowData.get("First Name"));
            checkoutStepOnePageUI.inputLastName().sendKeys(rowData.get("Last Name"));
            checkoutStepOnePageUI.inputCode().sendKeys(rowData.get("Zip/Postal Code"));

        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi điền form: " + e.getMessage());
        }
    }

    @Test(dataProvider = "demoData")
    public void testInformationSuccessful(Map<String, String> rowData) {
        fillForm(rowData);
        checkoutStepOnePageUI.buttonContinue().click();
    }

    @Test(dataProvider = "demoData")
    public void testContinueNull(Map<String, String> rowData) {
        checkoutStepOnePageUI.buttonContinue().click();
    }

    @Test(dataProvider = "demoData")
    public void testFirstNameNull(Map<String, String> rowData) {
        rowData.put("First Name", "");
        fillForm(rowData);
        checkoutStepOnePageUI.buttonContinue().click();

        String error = checkoutStepOnePageUI.errorMessage().getText();
        Assert.assertEquals(error, "Error: First Name is required");
    }

    @Test(dataProvider = "demoData")
    public void testLastNameNull(Map<String, String> rowData) {
        rowData.put("Last Name", "");
        fillForm(rowData);
        checkoutStepOnePageUI.buttonContinue().click();

        String error = checkoutStepOnePageUI.errorMessage().getText();
        Assert.assertEquals(error, "Error: Last Name is required");
    }

    @Test(dataProvider = "demoData")
    public void testZipCodeNull(Map<String, String> rowData) {
        rowData.put("Zip/Postal Code", "");
        fillForm(rowData);
        checkoutStepOnePageUI.buttonContinue().click();

        String error = checkoutStepOnePageUI.errorMessage().getText();
        Assert.assertEquals(error, "Error: Postal Code is required");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
