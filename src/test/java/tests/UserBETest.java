package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.AddressModel;
import models.RequestUserModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserBETest {

    @Test
    public void userTest() {
        RestAssured.baseURI = "https://api.practicesoftwaretesting.com";
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Accept", "application/json");

        AddressModel addressModel = new AddressModel("Street 1", "City", "State", "Country", "1234AA");
        RequestUserModel requestBody = new RequestUserModel("Teodora", "Doe", addressModel, "0987654321", "1970-01-01", "SuperSecure@123", "teo1@doeexample.com");

        request.body(requestBody);
        Response response = request.post("/users/register");
        System.out.println(response.getStatusLine());
        response.body().prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 201);

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.practicesoftwaretesting.com/auth/login");
        driver.manage().window().maximize();

        WebElement emailElement = driver.findElement(By.id("email"));
        emailElement.sendKeys(requestBody.getEmail());

        WebElement passwordElement = driver.findElement(By.id("password"));
        passwordElement.sendKeys(requestBody.getPassword());

        WebElement loginButton = driver.findElement(By.xpath("//input[@type='submit']"));
        loginButton.click();
    }
}
