package com.exercise.tests;

import com.exercise.pages.GoogleHomePage;
import com.exercise.util.PropManager;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import com.exercise.util.WebDriverManager;
import java.util.Properties;

public class GoogleHomePageTest {
    WebDriver driver;
    WebDriverManager driverManager;
    static Properties prop;

    @BeforeClass
     public static void initialSetup() {
        prop = PropManager.getProps();
    }

    @Before
    public void setup() {
        driverManager = new WebDriverManager();
        driverManager.initWebDriver(prop.getProperty("BrowserToExecute"));
        driver = driverManager.getDriver();

    }

    @Test
    public void testSearchFunctionality() {
        GoogleHomePage homePage = new GoogleHomePage();
        driver.get(prop.getProperty("googleHomePageURL"));
        String searchKeyWord = "Selenium";
        homePage = homePage.getInstance(driver);
        homePage.enterSearchText(searchKeyWord);
        homePage.clickSearchButton();
        Assert.assertEquals("Selenium - Google Search", driver.getTitle());
    }

    @After
    public void tearDown() {
        driverManager.shutDownDriver();
    }
}
