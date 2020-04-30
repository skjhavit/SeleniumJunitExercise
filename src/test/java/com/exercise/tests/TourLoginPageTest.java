package com.exercise.tests;

import com.exercise.pages.ToursHomePage;
import com.exercise.pages.ToursSignOnPage;
import com.exercise.util.PropManager;
import com.exercise.util.RepetetiveActions;
import com.exercise.util.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Properties;

public class TourLoginPageTest {
    WebDriverManager driverManager;
    WebDriver driver;
    static Properties prop;
    RepetetiveActions actions;

    @BeforeClass
    public static void initialSetup() {
        prop = PropManager.getProps();
    }

    @Before
    public void setup() {
        driverManager = new WebDriverManager();
        driverManager.initWebDriver(prop.getProperty("BrowserToExecute"));
        driver = driverManager.getDriver();
        actions = new RepetetiveActions();
    }

    @Test
    public void testCurrentDateandFeaturedDestination() {
        ToursHomePage homePage = actions.LoadHomePage(driver);
        String currentDate = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).format(LocalDateTime.now());
        Assert.assertEquals(currentDate, homePage.getCurrentDate());
        Assert.assertEquals("Aruba", homePage.getFeaturedDestination().split(":")[1].trim());
    }

    @Test
    public void incorrectLoginScenario() {
        actions.IncorrectLogin(driver);
        ToursSignOnPage signOnPage = new ToursSignOnPage();
        signOnPage = signOnPage.getInstance(driver);
        Boolean condition = signOnPage.getSignOnImage().equalsIgnoreCase(prop.getProperty("sectionImageURL")) && signOnPage.getSignOnSectionText().trim().equalsIgnoreCase(prop.getProperty("sectionText"));
        Assert.assertEquals(Boolean.TRUE, condition);
    }

    @Test
    public void validLoginScenario() {
        actions.LoginToHomePage(driver);
        Assert.assertEquals(prop.getProperty("finderImageURL"), driver.findElement(By.xpath("//img[@src='/images/masts/mast_flightfinder.gif']")).getAttribute("src"));
        Assert.assertEquals(prop.getProperty("flightPageURL"), driver.getCurrentUrl().split("\\?")[0].trim());

    }

    @After
    public void tearDown() {
        driverManager.shutDownDriver();
    }
}
