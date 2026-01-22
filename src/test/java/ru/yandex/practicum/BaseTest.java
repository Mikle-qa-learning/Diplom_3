package ru.yandex.practicum;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.pageObject.HomePage;
import ru.yandex.practicum.utils.DriverHelper;
import ru.yandex.practicum.utils.Url;

import java.io.IOException;

public class BaseTest {
    protected WebDriver webDriver;
    protected HomePage homePage;

    @Before
    public void setUp() throws IOException {
        DriverHelper driverHelper = new DriverHelper();
        webDriver = driverHelper.initDriver();
        webDriver.get(Url.HOME_URL);
        homePage = new HomePage(webDriver);
    }

    @After
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}