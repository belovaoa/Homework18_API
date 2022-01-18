package com.belovaoa;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class DemowebshopTests extends TestBase {

    @Test
    @BeforeEach
    void getCookiesAndSetItToBrowserByAPI() {

        String authorizationCookie =
                given()
                        .contentType("application/x-www-form-urlencoded")
                        .formParam("testfordemowebshop@mail.ru")
                        .formParam("6210test")
                        .when()
                        .post("/login")
                        .then()
                        .statusCode(200)
                        .extract()
                        .cookie("NOPCOMMERCE.AUTH");
        open("/Themes/DefaultClean/Content/images/logo.png");
        getWebDriver().manage().addCookie(
                new Cookie("NOPCOMMERCE.AUTH", authorizationCookie));
        open("/customer/info");
        $("#Email").shouldHave(text("testfordemowebshop@mail.ru"));
    }

    @Disabled
    @Test
    void addToCart() {
        String data = "product_attribute_72_5_18=53&product_attribute_72_6_19=54" +
                "&product_attribute_72_3_20=57&addtocart_72.EnteredQuantity=1";
        Response response =
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie("NOPCOMMERCE.AUTH")
                .body(data)
                .when()
                .post("/addproducttocart/details/72/1")
                .then()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
                .extract().response();
        System.out.println(response);
    }
}
