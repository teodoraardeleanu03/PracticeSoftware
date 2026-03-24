package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.LogUtility;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "email")
    private WebElement emailElement;

    @FindBy(id = "password")
    private WebElement passwordElement;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement loginButton;

    public void loginProcess(String email, String password) {
        emailElement.sendKeys(email);
        LogUtility.infoLog("The user entered email: " + email);
        passwordElement.sendKeys(password);
        LogUtility.infoLog("The user entered password: " + password);
        loginButton.click();
        LogUtility.infoLog("The user clicked on login button");
    }

}
