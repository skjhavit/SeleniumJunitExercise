package com.exercise.util;

import com.exercise.pages.FlightFinderPage;
import com.exercise.pages.ToursHomePage;
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

}
