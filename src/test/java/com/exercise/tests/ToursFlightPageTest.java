package com.exercise.tests;

import com.exercise.pages.FlightFinderPage;
import com.exercise.pages.GoogleHomePage;
import com.exercise.pages.SelectFlightPage;
import com.exercise.pages.ToursHomePage;
import com.exercise.util.PropManager;
import com.exercise.util.RepetetiveActions;
import com.exercise.util.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class ToursFlightPageTest {
    WebDriver driver;
    WebDriverManager driverManager;
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
        actions.LoginToHomePage(driver);
    }

    @Test
    public void testFlightSearchFunctionality() {
        FlightFinderPage finderPage = new FlightFinderPage();
        finderPage = finderPage.getInstance(driver);
        Assert.assertEquals(Boolean.TRUE, finderPage.isDepartingFromOptionPresent(prop.getProperty("toDestination")));
        Assert.assertEquals(Boolean.TRUE, finderPage.isDepartingFromOptionPresent(prop.getProperty("fromDestination")));
        actions.selectSourceAndDestination(finderPage);
        Assert.assertEquals("Select a Flight: Mercury Tours", driver.getTitle());
    }

    @Test
    public void testSelectFLightFunctionality() {
        FlightFinderPage finderPage = new FlightFinderPage();
        finderPage = finderPage.getInstance(driver);
        actions.selectSourceAndDestination(finderPage);
        SelectFlightPage flightPage = new SelectFlightPage();
        flightPage = flightPage.getInstance(driver);
        Assert.assertEquals(Boolean.TRUE, flightPage.isSortedByPrice());
        Assert.assertEquals(Boolean.TRUE, flightPage.checkHeaderColors(prop.getProperty("headerColor")));
        flightPage.selectSecondHeighestPrice(Integer.parseInt(prop.getProperty("priceOrder")));
        flightPage.clickContinue();
    }

    @After
    public void tearDown() {
        driverManager.shutDownDriver();
    }
}
