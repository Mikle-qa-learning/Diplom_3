package ru.yandex.practicum.pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    WebDriver webDriver;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    private final By enterFormHeading = By.xpath(".//*[text()='Вход']");
    private final By emailInputField = By.xpath(".//fieldset[1]//input");
    private final By passwordInputField = By.xpath(".//fieldset[2]//input");

    private final By enterButton = By.xpath(".//button[text()='Войти']");

    private final By registrationLink = By.className("Auth_link__1fOlj");

    private final By restorePasswordLink = By.xpath(".//a[text()='Восстановить пароль']");

    @Step("Ожидание загрузки страницы входа")
    public void waitForPageLoad(){
        new WebDriverWait(webDriver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(enterFormHeading));
    }

    @Step("Заполнить данные пользователя: email={email}, пароль={password}")
    public void fillInUserData(String email, String password) {
        webDriver.findElement(emailInputField).sendKeys(email);
        webDriver.findElement(passwordInputField).sendKeys(password);
    }

    @Step("Нажать кнопку 'Войти'")
    public void clickEnterButton(){
        webDriver.findElement(enterButton).click();
    }

    @Step("Нажать ссылку 'Зарегистрироваться'")
    public void clickRegistrationLink(){
        webDriver.findElement(registrationLink).click();
    }

    @Step("Нажать ссылку 'Восстановить пароль'")
    public void clickRestorePasswordLink(){
        webDriver.findElement(restorePasswordLink).click();
    }

}
