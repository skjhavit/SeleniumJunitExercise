package com.exercise.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import java.util.Optional;

public class FlightFinderPage {
    WebDriver driver;

    @FindBy(how = How.NAME, using = "fromPort")
    WebElement departingFrom;

    @FindBy(how = How.NAME, using = "toPort")
    WebElement arrivingIn;

    @FindBy(how = How.NAME, using = "servClass")
    List<WebElement> serviceClass;

    @FindBy(how = How.NAME, using = "airline")
    WebElement preferences;

    @FindBy(how = How.NAME, using = "findFlights")
    WebElement submitFlight;

    @FindBy(how = How.NAME, using = "tripType")
    WebElement tripType;

    public FlightFinderPage getInstance(WebDriver driver) {
        this.driver = driver;
        return PageFactory.initElements(driver, FlightFinderPage.class);
    }

    public Boolean isDepartingFromOptionPresent(String from) {
        return new Select(departingFrom).getOptions().stream().filter(webElement -> webElement.getText().equalsIgnoreCase(from)).findFirst().isPresent();
    }

    public void selectDepartingFromOption(String from) {
        new Select(departingFrom).selectByValue(from);
    }

    public void selectArrivingToOption(String to) {
        new Select(arrivingIn).selectByValue(to);
    }

    public void selectServiceClass(String serviceClass) {
        Optional<WebElement> service = this.serviceClass.stream().filter(webElement -> webElement.getAttribute("value").equalsIgnoreCase(serviceClass)).findFirst();
        if(service.isPresent() && !service.get().isSelected())
            service.get().click();
    }
    public void selectTripType(String tripType) {
        Optional<WebElement> service = this.serviceClass.stream().filter(webElement -> webElement.getAttribute("value").equalsIgnoreCase(tripType)).findFirst();
        if(service.isPresent() && !service.get().isSelected())
            service.get().click();
    }

    public void submitSearch() {
        this.submitFlight.click();
    }
}
