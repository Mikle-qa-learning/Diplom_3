package ru.yandex.practicum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ConstructorTests extends BaseTest {

    @Test
    @DisplayName("Переключение на раздел 'Булки'")
    public void testSwitchToBunsSection() {

        homePage.clickSaucesLink();
        homePage.waitForSaucesSectionActive();

        assertTrue(homePage.isOnlySaucesSectionActive());

        homePage.clickBunsLink();
        homePage.waitForBunsSectionActive();

        assertTrue(homePage.isOnlyBunsSectionActive());
    }

    @Test
    @DisplayName("Переключение на раздел 'Соусы'")
    public void testSwitchToSaucesSection() {

        homePage.waitForBunsSectionActive();

        assertTrue(homePage.isOnlyBunsSectionActive());

        homePage.clickSaucesLink();
        homePage.waitForSaucesSectionActive();

        assertTrue(homePage.isOnlySaucesSectionActive());
    }

    @Test
    @DisplayName("Переключение на раздел 'Начинки'")
    public void testSwitchToFillingsSection() {

        homePage.waitForBunsSectionActive();

        assertTrue(homePage.isOnlyBunsSectionActive());

        homePage.clickFillingsLink();
        homePage.waitForFillingsSectionActive();

        assertTrue(homePage.isOnlyFillingsSectionActive());
    }

}
