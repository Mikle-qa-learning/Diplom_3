package ru.yandex.practicum.pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    WebDriver webDriver;
    private final By enterAccountButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By registerOrderButton = By.xpath(".//*[text()='Оформить заказ']");
    private final By personalProfileButton = By.xpath(".//*[text()='Личный Кабинет']");
    private final By saucesLink = By.xpath(".//span[text()='Соусы']/parent::div");
    private final By bunsLink = By.xpath(".//span[text()='Булки']/parent::div");
    private final By fillingLink = By.xpath(".//*[text()='Начинки']/parent::div");
    private final String ACTIVE_CLASS = "tab_tab_type_current__2BEPc";

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Нажать кнопку 'Войти в аккаунт'")
    public void clickEnterAccountButton() {
        webDriver.findElement(enterAccountButton).click();
    }

    @Step("Ожидание появления кнопки 'Войти в аккаунт'")
    public void waitForEnterAccountButton(){
        new WebDriverWait(webDriver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(enterAccountButton));
    }

    @Step("Ожидание появления кнопки 'Оформить заказ'")
    public void waitForRegisterOrderButton(){
        new WebDriverWait(webDriver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(registerOrderButton));
    }

    @Step("Ожидание появления кнопки 'Личный Кабинет'")
    public void waitForPersonalProfileButton(){
        new WebDriverWait(webDriver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(personalProfileButton));
    }

    @Step("Перейти в личный кабинет")
    public void enterPersonalProfile(){
        webDriver.findElement(personalProfileButton).click();
    }

    @Step("Нажать на раздел 'Булки'")
    public void clickBunsLink() {
        webDriver.findElement(bunsLink).click();
    }

    @Step("Нажать на раздел 'Соусы'")
    public void clickSaucesLink() {
        webDriver.findElement(saucesLink).click();
    }

    @Step("Нажать на раздел 'Начинки'")
    public void clickFillingsLink(){
        webDriver.findElement(fillingLink).click();
    }

    @Step("Получить название класса для раздела 'Булки'")
    public String getClassNameOfBunsLink() {
        return webDriver.findElement(bunsLink).getAttribute("class");
    }

    @Step("Получить название класса для раздела 'Соусы'")
    public String getClassNameOfSaucesLink(){
        return webDriver.findElement(saucesLink).getAttribute("class");
    }

    @Step("Получить название класса для раздела 'Начинки'")
    public String getClassNameOfFillingsLink(){
        return webDriver.findElement(fillingLink).getAttribute("class");
    }

    @Step("Проверить, что раздел 'Булки' активен")
    public boolean isBunsSectionActive() {
        return getClassNameOfBunsLink().contains(ACTIVE_CLASS);
    }

    @Step("Проверить, что раздел 'Соусы' активен")
    public boolean isSaucesSectionActive() {
        return getClassNameOfSaucesLink().contains(ACTIVE_CLASS);
    }

    @Step("Проверить, что раздел 'Начинки' активен")
    public boolean isFillingsSectionActive() {
        return getClassNameOfFillingsLink().contains(ACTIVE_CLASS);
    }

    @Step("Проверить, что раздел 'Булки' неактивен")
    public boolean isBunsSectionInactive() {
        return !isBunsSectionActive();
    }

    @Step("Проверить, что раздел 'Соусы' неактивен")
    public boolean isSaucesSectionInactive() {
        return !isSaucesSectionActive();
    }

    @Step("Проверить, что раздел 'Начинки' неактивен")
    public boolean isFillingsSectionInactive() {
        return !isFillingsSectionActive();
    }

    @Step("Проверить, что активен только раздел 'Булки'")
    public boolean isOnlyBunsSectionActive() {
        return isBunsSectionActive() && isSaucesSectionInactive() && isFillingsSectionInactive();
    }

    @Step("Проверить, что активен только раздел 'Соусы'")
    public boolean isOnlySaucesSectionActive() {
        return isSaucesSectionActive() && isBunsSectionInactive() && isFillingsSectionInactive();
    }

    @Step("Проверить, что активен только раздел 'Начинки'")
    public boolean isOnlyFillingsSectionActive() {
        return isFillingsSectionActive() && isBunsSectionInactive() && isSaucesSectionInactive();
    }

    @Step("Ожидание активации раздела 'Булки'")
    public void waitForBunsSectionActive() {
        new WebDriverWait(webDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.attributeContains(webDriver.findElement(bunsLink), "class", ACTIVE_CLASS));
    }

    @Step("Ожидание активации раздела 'Соусы'")
    public void waitForSaucesSectionActive() {
        new WebDriverWait(webDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.attributeContains(webDriver.findElement(saucesLink), "class", ACTIVE_CLASS));
    }

    @Step("Ожидание активации раздела 'Начинки'")
    public void waitForFillingsSectionActive() {
        new WebDriverWait(webDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.attributeContains(webDriver.findElement(fillingLink), "class", ACTIVE_CLASS));
    }

}
