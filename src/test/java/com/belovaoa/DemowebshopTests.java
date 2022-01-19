package com.belovaoa;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class DemowebshopTests extends TestBase {

    @Test
    @BeforeEach
    void getCookiesAndSetItToBrowserByAPIthenAddProductAndCheckValueInBrowser() {

        String cookie =
                given()
                        .contentType("application/x-www-form-urlencoded")
                        .formParam("testfordemowebshop@mail.ru")
                        .formParam("6210test")
                        .when()
                        .post("login")
                        .then()
                        .statusCode(200)
                        .extract()
                        .cookie("Nop.customer");
        open("Themes/DefaultClean/Content/images/logo.png");
        getWebDriver().manage().addCookie(
                new Cookie("Nop.customer", cookie));
        open("");

                given()
                        .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                        .cookie("Nop.customer", "cookie")
                        .body("product_attribute_72_5_18=53&product_attribute_72_6_19=54" +
                                "&product_attribute_72_3_20=57&addtocart_72.EnteredQuantity=1")
                        .when()
                        .post("addproducttocart/details/72/1")
                        .then()
                        .statusCode(200)
                        .body("success", is(true))
                        .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
                        .extract().response();
    }
}
