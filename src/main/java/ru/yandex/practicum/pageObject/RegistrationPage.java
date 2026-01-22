package ru.yandex.practicum.pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {
    WebDriver webDriver;

    public RegistrationPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    private final By registerFormHeading = By.xpath(".//*[text()='Регистрация']");

    private final By nameInputField = By.xpath(".//fieldset[1]//input");

    private final By emailInputField = By.xpath(".//fieldset[2]//input");
    private final By passwordInputField = By.xpath(".//fieldset[3]//input");

    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");

    private final By passwordFieldError = By.xpath(".//fieldset[3]//p");

    private final By alreadyRegisteredLink = By.xpath(".//*[text()='Уже зарегистрированы?']/a");

    @Step("Ожидание загрузки страницы регистрации")
    public void waitForPageLoad(){
        new WebDriverWait(webDriver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(registerFormHeading));
    }

    @Step("Заполнить форму регистрации: имя={name}, email={email}, пароль={password}")
    public void fillInRegistrationForm(String name, String email, String password){
        webDriver.findElement(nameInputField).sendKeys(name);
        webDriver.findElement(emailInputField).sendKeys(email);
        webDriver.findElement(passwordInputField).sendKeys(password);
        webDriver.findElement(registerButton).click();
    }

    @Step("Получить текст ошибки поля пароля")
    public String getPasswordFieldErrorText() {
        return webDriver.findElement(passwordFieldError).getText();
    }

    @Step("Нажать ссылку 'Войти'")
    public void clickAlreadyRegisteredLink(){
        webDriver.findElement(alreadyRegisteredLink).click();
    }
}
