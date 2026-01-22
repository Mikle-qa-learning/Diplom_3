package ru.yandex.practicum.pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    WebDriver webDriver;

    public ProfilePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    private final By textMessage = By.xpath(".//*[text()='В этом разделе вы можете изменить свои персональные данные']");

    private final By nameField = By.xpath(".//li[1]//input");
    private final By emailField = By.xpath(".//li[2]//input");

    @Step("Ожидание загрузки страницы профиля")
    public void waitForPageLoad() {
        new WebDriverWait(webDriver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(textMessage));
    }

    @Step("Получить email из профиля")
    public String getEmailText() {
        return webDriver.findElement(emailField).getAttribute("value");
    }

    @Step("Получить имя из профиля")
    public String getNameText() {
        return webDriver.findElement(nameField).getAttribute("value");
    }

}
