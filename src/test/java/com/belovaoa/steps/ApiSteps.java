package com.belovaoa.steps;

import com.belovaoa.Spec;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;

public class ApiSteps extends Spec {

    @Step("Проверка поиска")
    public ValidatableResponse checkSearch() {
        String valueSearch = "science";

        open("books");
        return given()
                .spec(request)
                .when()
                .get("/search?q=" + valueSearch)
                .then()
                .spec(response);
    }
}
