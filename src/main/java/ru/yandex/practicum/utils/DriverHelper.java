package ru.yandex.practicum.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Properties;

public class DriverHelper {

    protected WebDriver driver;

    public WebDriver initDriver() throws IOException {
       Properties properties = new Properties();
       properties.load(new FileInputStream("src/test/resources/browser.properties"));
       String browserProperty = properties.getProperty("testBrowser");
       System.out.println("browserProperty = " + browserProperty);
       BrowserType browserType = BrowserType.valueOf(browserProperty);
       switch (browserType){
           case CHROME:
               driver = new ChromeDriver();
               break;
           case YANDEX:
               System.setProperty("webdriver.chrome.driver", DriverPath.YANDEX_DRIVER_WIN_PATH);
               ChromeOptions options = new ChromeOptions();
               options.setBinary(DriverPath.YANDEX_BINARY_PATH);
               driver = new ChromeDriver(options);
               break;
           default:
               throw new RemoteException("Browser undefined");
       }
return driver;
    }

}

