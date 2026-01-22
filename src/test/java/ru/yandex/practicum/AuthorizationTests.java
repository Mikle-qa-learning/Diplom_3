package ru.yandex.practicum;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import net.datafaker.Faker;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.pageObject.LoginPage;
import ru.yandex.practicum.pageObject.ProfilePage;
import ru.yandex.practicum.pageObject.RegistrationPage;
import ru.yandex.practicum.pageObject.RestorePasswordPage;
import ru.yandex.practicum.user.User;
import ru.yandex.practicum.user.UserClient;
import ru.yandex.practicum.utils.Url;

public class AuthorizationTests extends BaseTest {

    LoginPage loginPage;
    ProfilePage profilePage;
    RegistrationPage registrationPage;
    RestorePasswordPage restorePasswordPage;

    Faker faker;
    String email;
    String password;
    String name;
    String accessToken;
    UserClient userClient = new UserClient();
    User user;

    @Before
    public void createUser() {
        faker = new Faker();

        name = faker.name().fullName();
        email = faker.internet().emailAddress();
        password = faker.internet().password(6, 10);
        user = new User(email, password, name);
        Response createUser = userClient.createUser(user);
        accessToken = createUser.body().path("accessToken").toString().substring(7);
    }

    @Test
    @DisplayName("Вход через кнопку 'Войти в аккаунт' на главной странице")
    public void testEnterViaEnterAccountButton() {
        homePage.waitForEnterAccountButton();
        homePage.clickEnterAccountButton();

        loginPage = new LoginPage(webDriver);
        loginPage.waitForPageLoad();
        loginPage.fillInUserData(email, password);
        loginPage.clickEnterButton();

        homePage.waitForRegisterOrderButton();
        Assert.assertEquals(Url.HOME_URL, webDriver.getCurrentUrl());

        homePage.enterPersonalProfile();
        profilePage = new ProfilePage(webDriver);
        profilePage.waitForPageLoad();
        Assert.assertEquals(email, profilePage.getEmailText());
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет' на главной странице")
    public void testEnterViaPersonalProfileButton() {
        homePage.waitForPersonalProfileButton();
        homePage.enterPersonalProfile();

        loginPage = new LoginPage(webDriver);
        loginPage.waitForPageLoad();
        loginPage.fillInUserData(email, password);
        loginPage.clickEnterButton();

        homePage.waitForRegisterOrderButton();
        homePage.enterPersonalProfile();

        profilePage = new ProfilePage(webDriver);
        profilePage.waitForPageLoad();
        Assert.assertEquals(email, profilePage.getEmailText());
    }

    @Test
    @DisplayName("Вход через ссылку в форме регистрации")
    public void testEnterViaLinkInRegistrationForm() {
        homePage.waitForEnterAccountButton();
        homePage.clickEnterAccountButton();

        loginPage = new LoginPage(webDriver);
        loginPage.waitForPageLoad();
        loginPage.clickRegistrationLink();

        registrationPage = new RegistrationPage(webDriver);
        registrationPage.waitForPageLoad();
        registrationPage.clickAlreadyRegisteredLink();

        loginPage.waitForPageLoad();
        loginPage.fillInUserData(email, password);
        loginPage.clickEnterButton();

        homePage.waitForRegisterOrderButton();
        homePage.enterPersonalProfile();

        profilePage = new ProfilePage(webDriver);
        profilePage.waitForPageLoad();
        Assert.assertEquals(email, profilePage.getEmailText());
    }

    @Test
    @DisplayName("Вход через ссылку в форме восстановления пароля")
    public void testEnterViaLinkInRestorePasswordForm() {
        homePage.waitForEnterAccountButton();
        homePage.clickEnterAccountButton();

        loginPage = new LoginPage(webDriver);
        loginPage.waitForPageLoad();
        loginPage.clickRestorePasswordLink();

        restorePasswordPage = new RestorePasswordPage(webDriver);
        restorePasswordPage.waitForPageLoad();
        restorePasswordPage.clickRememberedPassword();

        loginPage.waitForPageLoad();
        loginPage.fillInUserData(email, password);
        loginPage.clickEnterButton();

        homePage.waitForRegisterOrderButton();
        homePage.enterPersonalProfile();

        profilePage = new ProfilePage(webDriver);
        profilePage.waitForPageLoad();
        Assert.assertEquals(email, profilePage.getEmailText());
    }

    @After
    public void cleanUp() {
        userClient.deleteUser(accessToken);
    }
}
