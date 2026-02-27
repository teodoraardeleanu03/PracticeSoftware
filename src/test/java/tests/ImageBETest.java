package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.ImageService;

public class ImageBETest {
    @Test
    public void testMethod() {
        // Pasul 1: Accesam toate imaginile
        ImageService imageService = new ImageService();
        imageService.obtainAllImages();
    }
}
