package com.exercise.util;

import com.exercise.pages.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class RepetetiveActions {
    Properties prop = PropManager.getProps();
    ToursHomePage homePage;

    public ToursHomePage LoadHomePage(WebDriver driver) {
        homePage = new ToursHomePage();
        driver.get(prop.getProperty("tourHomePage"));
        homePage = homePage.getInstance(driver);
        return homePage;
    }

    public void LoginToHomePage(WebDriver driver) {
        homePage = LoadHomePage(driver);
        homePage.typeUserName(prop.getProperty("userName"));
        homePage.typePassword(prop.getProperty("validPassword"));
        homePage.submitForm();
    }

    public void IncorrectLogin(WebDriver driver) {
        homePage = LoadHomePage(driver);
        homePage.typeUserName(prop.getProperty("userName"));
        homePage.typePassword(prop.getProperty("invalidPassword"));
        homePage.submitForm();
    }

    public void selectSourceAndDestination(FlightFinderPage finderPage) {
        finderPage.selectDepartingFromOption(prop.getProperty("fromDestination"));
        finderPage.selectArrivingToOption(prop.getProperty("toDestination"));
        finderPage.selectServiceClass(prop.getProperty("serviceClass"));
        finderPage.selectTripType(prop.getProperty("tripType"));
        finderPage.submitSearch();
    }

    public SelectFlightPage proceedToBookFlight(WebDriver driver) {
        FlightFinderPage finderPage = new FlightFinderPage();
        finderPage = finderPage.getInstance(driver);
        selectSourceAndDestination(finderPage);
        SelectFlightPage flightPage = new SelectFlightPage();
        flightPage = flightPage.getInstance(driver);
        Assert.assertEquals(Boolean.TRUE, flightPage.isSortedByPrice());
        Assert.assertEquals(Boolean.TRUE, flightPage.checkHeaderColors(prop.getProperty("headerColor")));
        flightPage.selectSecondHeighestPrice(Integer.parseInt(prop.getProperty("priceOrder")));
        return flightPage;
    }

    public ConfirmationPage getConfirmationPage(WebDriver driver) {
        BookFlightPage bookFlightPage =  new BookFlightPage();
        bookFlightPage = bookFlightPage.getInstance(driver);
        bookFlightPage.bookFlight(prop);
        ConfirmationPage confirmationPage = new ConfirmationPage();
        confirmationPage =  confirmationPage.getInstance(driver);
        return confirmationPage;
    }

}
