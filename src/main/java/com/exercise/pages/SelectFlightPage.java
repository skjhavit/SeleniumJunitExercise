package com.exercise.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SelectFlightPage {
    WebDriver driver;

    @FindBy(how = How.NAME, using = "outFlight")
    List<WebElement> departingFlights;

    @FindBy(how = How.NAME, using = "inFlight")
    List<WebElement> arrivingFlights;

    @FindBy(how = How.XPATH, using = "//td[@class='frame_header_info']")
    List<WebElement> headerElements;

    @FindBy(how = How.NAME, using = "reserveFlights")
    WebElement continueButton;

    public SelectFlightPage getInstance(WebDriver driver) {
        this.driver = driver;
        return PageFactory.initElements(driver, SelectFlightPage.class);
    }

    public Boolean checkHeaderColors(String color) {
        return headerElements.stream().filter(webElement -> webElement.getAttribute("bgcolor").equalsIgnoreCase(color)).count() == headerElements.size();
    }

    public Boolean isSortedByPrice() {
        ArrayList<Float> departurePrices = (ArrayList<Float>) this.departingFlights.stream().map(webElement -> Float.parseFloat(webElement.getAttribute("value").split("\\$")[2])).collect(Collectors.toList());
        ArrayList<Float> arrivalPrices = (ArrayList<Float>) this.arrivingFlights.stream().map(webElement -> Float.parseFloat(webElement.getAttribute("value").split("\\$")[2])).collect(Collectors.toList());
        return comparePrices(departurePrices) && comparePrices(arrivalPrices);
    }

    Boolean comparePrices(ArrayList<Float> originalPrice) {
        ArrayList<Float> tmpPrice = new ArrayList<>(originalPrice);
        Collections.sort(tmpPrice);
        return  tmpPrice.equals(originalPrice);
    }

    public void selectSecondHeighestPrice(int order) {
        if(!this.departingFlights.get(order-1).isSelected()) {
            this.departingFlights.get(order-1).click();
        }
        if(!this.arrivingFlights.get(order-1).isSelected()) {
            this.arrivingFlights.get(order-1).click();
        }
    }

    public void clickContinue() {
        this.continueButton.click();
    }
}
