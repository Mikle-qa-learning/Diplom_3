package ru.yandex.practicum.pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RestorePasswordPage {
    WebDriver webDriver;

    public RestorePasswordPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    private final By restoreFormHeading = By.xpath(".//*[text()='Восстановление пароля']");


    private final By rememberedPasswordLink = By.xpath(".//a[text()='Войти']");

    @Step("Ожидание загрузки страницы восстановления пароля")
    public void waitForPageLoad(){
        new WebDriverWait(webDriver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(restoreFormHeading));
    }

    @Step("Нажать ссылку 'Войти'")
    public void clickRememberedPassword(){
        webDriver.findElement(rememberedPasswordLink).click();
    }
}