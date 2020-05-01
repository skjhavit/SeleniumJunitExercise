package com.exercise.tests;

import com.exercise.pages.*;
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

    @Test
    public void testFlightBooking() {
        SelectFlightPage flightPage = actions.proceedToBookFlight(driver);
        flightPage.clickContinue();
        BookFlightPage bookFlightPage =  new BookFlightPage();
        bookFlightPage = bookFlightPage.getInstance(driver);
        Assert.assertEquals(Boolean.TRUE, bookFlightPage.isInboundFlightCorrect(flightPage.getInFlightNumber()));
        Assert.assertEquals(Boolean.TRUE, bookFlightPage.isOutboundFlightCorrect(flightPage.getOutFlightNumber()));
        Assert.assertEquals(Boolean.TRUE, bookFlightPage.isTotalCorrect());
        bookFlightPage.bookFlight(prop);
    }

    @Test
    public void testConfirmationPage() {
        SelectFlightPage flightPage = actions.proceedToBookFlight(driver);
        flightPage.clickContinue();
        ConfirmationPage confirmationPage = actions.getConfirmationPage(driver);
        Assert.assertEquals(flightPage.getOutFlightNumber(), confirmationPage.getOutboundFlightName());
        Assert.assertEquals(flightPage.getInFlightNumber(), confirmationPage.getInboundFlightName());
        System.out.println("Confirmation Number is : "+confirmationPage.getConfirmationNumber());
        confirmationPage.signMeOff();


    }

    @After
    public void tearDown() {
        driverManager.shutDownDriver();
    }
}
