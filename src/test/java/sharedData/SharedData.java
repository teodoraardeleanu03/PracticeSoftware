package sharedData;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class SharedData {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod
    public void prepareEnvironment() {
        driver = new ChromeDriver();
        driver.get("https://www.practicesoftwaretesting.com/auth/login");
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void clearEnvironment() {
        driver.quit();
    }
}
