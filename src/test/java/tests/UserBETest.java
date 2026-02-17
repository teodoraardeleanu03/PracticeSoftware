package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
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

        String requestBody = "{\n" +
                "  \"first_name\": \"Teodora\",\n" +
                "  \"last_name\": \"Doe\",\n" +
                "  \"address\": {\n" +
                "    \"street\": \"Street 1\",\n" +
                "    \"city\": \"City\",\n" +
                "    \"state\": \"State\",\n" +
                "    \"country\": \"Country\",\n" +
                "    \"postal_code\": \"1234AA\"\n" +
                "  },\n" +
                "  \"phone\": \"0987654321\",\n" +
                "  \"dob\": \"1970-01-01\",\n" +
                "  \"password\": \"SuperSecure@123\",\n" +
                "  \"email\": \"teo1@doeexample.com\"\n" +
                "}";

        request.body(requestBody);
        Response response = request.post("/users/register");
        System.out.println(response.getStatusLine());
        response.body().prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 201);

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.practicesoftwaretesting.com/auth/login");
        driver.manage().window().maximize();

        WebElement emailElement = driver.findElement(By.id("email"));
        emailElement.sendKeys("teo1@doeexample.com");

        WebElement passwordElement = driver.findElement(By.id("password"));
        passwordElement.sendKeys("SuperSecure@123");

        WebElement loginButton = driver.findElement(By.xpath("//input[@type='submit']"));
        loginButton.click();
    }
}
