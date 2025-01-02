package action;

import org.openqa.selenium.WebDriver;
import ui.LoginPageUI;

public class Login {
    WebDriver driver;
    LoginPageUI loginPageUI;
    public Login(WebDriver driver) {
        this.driver = driver;
        loginPageUI = new LoginPageUI(driver);
    }

    public void login() {
        loginPageUI.inputUserName().sendKeys("standard_user");
        loginPageUI.inputPassWord().sendKeys("secret_sauce");
        loginPageUI.buttonLogin().click();
    }

}
