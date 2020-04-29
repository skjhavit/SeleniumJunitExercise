package com.exercise.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverManager {
    WebDriver driver;
    String propertyName;
    public void initWebDriver(String driverType) {
        switch (driverType) {
            case "chrome":
                propertyName = "webdriver.chrome.driver";
                System.setProperty(propertyName, PropManager.getProps().getProperty("chromeDriverPath"));
                driver = new ChromeDriver();
                break;
            case "firefox":
                propertyName = "webdriver.gecko.driver";
                System.setProperty(propertyName, PropManager.getProps().getProperty("firefoxDriverPath"));
                driver = new FirefoxDriver();
                break;
            default:
                propertyName = "webdriver.gecko.driver";
                System.setProperty("webdriver.chrome.driver", PropManager.getProps().getProperty("chromeDriverPath"));
                driver = new ChromeDriver();
        }
        if(System.getProperty(propertyName).isEmpty()){
            System.out.println(" Driver path is not set, please set the driverpath first. Exiting the Application");
            System.exit(1);
        }

        this.setDriver(driver);
    }

    public  void shutDownDriver() {
        this.getDriver().quit();
    }

    public WebDriver getDriver() {
        return driver;
    }

    private void setDriver(WebDriver driver) {
        this.driver = driver;
    }
}
