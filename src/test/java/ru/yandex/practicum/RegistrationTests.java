package ru.yandex.practicum;

import io.qameta.allure.junit4.DisplayName;
import net.datafaker.Faker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.pageObject.LoginPage;
import ru.yandex.practicum.pageObject.ProfilePage;
import ru.yandex.practicum.pageObject.RegistrationPage;
import ru.yandex.practicum.utils.Url;

public class RegistrationTests extends BaseTest {

    LoginPage loginPage;
    RegistrationPage registrationPage;
    ProfilePage profilePage;

    Faker faker;
    String name;
    String email;
    String correctPassword;
    String wrongPassword;

    @Before
    public void setUpTestData() {
        faker = new Faker();

        name = faker.name().fullName();
        email = faker.internet().emailAddress();
        correctPassword = faker.internet().password(6, 10);
        wrongPassword = faker.lorem().characters(1, 5);
    }

    @Test
    @DisplayName("Регистрация с корректным паролем")
    public void testRegistrationWithCorrectPasswordSuccess() {

        homePage.waitForPersonalProfileButton();
        homePage.enterPersonalProfile();

        loginPage = new LoginPage(webDriver);
        loginPage.waitForPageLoad();
        loginPage.clickRegistrationLink();

        registrationPage = new RegistrationPage(webDriver);
        registrationPage.waitForPageLoad();
        registrationPage.fillInRegistrationForm(name, email, correctPassword);

        loginPage = new LoginPage(webDriver);
        loginPage.waitForPageLoad();
        Assert.assertEquals(Url.LOGIN_URL, webDriver.getCurrentUrl());

        loginPage.fillInUserData(email, correctPassword);
        loginPage.clickEnterButton();

        homePage.waitForPersonalProfileButton();
        homePage.enterPersonalProfile();

        profilePage = new ProfilePage(webDriver);
        profilePage.waitForPageLoad();
        Assert.assertEquals(name, profilePage.getNameText());
        Assert.assertEquals(email, profilePage.getEmailText());
    }

    @Test
    @DisplayName("Регистрация с коротким паролем")
    public void testRegistrationWithShortPasswordError() {
        String testName = faker.name().fullName();
        String testEmail = faker.internet().emailAddress();

        homePage.waitForPersonalProfileButton();
        homePage.enterPersonalProfile();

        loginPage = new LoginPage(webDriver);
        loginPage.waitForPageLoad();
        loginPage.clickRegistrationLink();

        registrationPage = new RegistrationPage(webDriver);
        registrationPage.waitForPageLoad();
        registrationPage.fillInRegistrationForm(testName, testEmail, wrongPassword);

        Assert.assertEquals("Некорректный пароль", registrationPage.getPasswordFieldErrorText());
        Assert.assertEquals(Url.REGISTER_URL, webDriver.getCurrentUrl());
    }
}
