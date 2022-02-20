package com.belovaoa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnotherDemowebshopTests extends TestBase{

    @Test
    @DisplayName("Проверка функционала - Поиск")
    void searchTest() {
        apiSteps.checkSearch();
    }
}
