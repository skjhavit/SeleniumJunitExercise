package com.exercise.tests;

import com.exercise.pages.FlightFinderPage;
import com.exercise.pages.GoogleHomePage;
import com.exercise.pages.SelectFlightPage;
import com.exercise.pages.ToursHomePage;
import com.exercise.util.PropManager;
import com.exercise.util.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class ToursFlightPageTest {
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
        ToursHomePage homePage = new ToursHomePage();
        driver.get(prop.getProperty("tourHomePage"));
        homePage = homePage.getInstance(driver);
        homePage.typeUserName(prop.getProperty("userName"));
        homePage.typePassword(prop.getProperty("validPassword"));
        homePage.submitForm();
    }

    @Test
    public void testFlightSearchFunctionality() {
        FlightFinderPage finderPage = new FlightFinderPage();
        finderPage = finderPage.getInstance(driver);
        Assert.assertEquals(Boolean.TRUE, finderPage.isDepartingFromOptionPresent(prop.getProperty("toDestination")));
        Assert.assertEquals(Boolean.TRUE, finderPage.isDepartingFromOptionPresent(prop.getProperty("fromDestination")));
        finderPage.selectDepartingFromOption(prop.getProperty("fromDestination"));
        finderPage.selectArrivingToOption(prop.getProperty("toDestination"));
        finderPage.selectServiceClass(prop.getProperty("serviceClass"));
        finderPage.selectTripType(prop.getProperty("tripType"));
        finderPage.submitSearch();
        Assert.assertEquals("Select a Flight: Mercury Tours", driver.getTitle());
    }

    @Test
    public void testSelectFLightFunctionality() {
        FlightFinderPage finderPage = new FlightFinderPage();
        finderPage = finderPage.getInstance(driver);
        finderPage.selectDepartingFromOption(prop.getProperty("fromDestination"));
        finderPage.selectArrivingToOption(prop.getProperty("toDestination"));
        finderPage.selectServiceClass(prop.getProperty("serviceClass"));
        finderPage.selectTripType(prop.getProperty("tripType"));
        finderPage.submitSearch();
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
