package com.belovaoa;

import com.belovaoa.config.UserCredential;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class DemowebshopTests extends TestBase {

    public static UserCredential credentials =
            ConfigFactory.create(UserCredential.class);

    @Test
    @DisplayName("Авторизация и добавление товара API+UI")
    void addProductGetCookiesAndSetItToBrowserByApiThenCheckValueInBrowser() {
        open("books");
        given()
                .cookie("Nop.customer=ee1baf75-daee-428b-ad29-a2b20005ba7b")
                .when()
                .post("addproducttocart/catalog/13/1/1")
                .then()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"));

        String cookie =
                given()
                        .contentType("application/x-www-form-urlencoded")
                        .formParam("Email", credentials.email())
                        .formParam("Password", credentials.password())
                        .when()
                        .post("login")
                        .then()
                        .statusCode(302)
                        .extract()
                        .cookie("NOPCOMMERCE.AUTH");
        open("Themes/DefaultClean/Content/images/logo.png");
        getWebDriver().manage().addCookie(
                new Cookie("NOPCOMMERCE.AUTH", cookie));
        open("");
        $(".account").shouldHave(text("testfordemowebshop@mail.ru"));
        $(".cart-qty").shouldHave(text("(1)"));
    }
}
