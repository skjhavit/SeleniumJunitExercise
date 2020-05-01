package com.exercise.pages;

import com.exercise.com.exercise.beans.UserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.util.Properties;

public class BookFlightPage {
    WebDriver driver;
    @FindBy(how = How.NAME, using = "inFlightName")
    WebElement selectedInfLightName;

    @FindBy(how = How.NAME, using = "inFlightNumber")
    WebElement selectedInfLightNumber;

    @FindBy(how = How.NAME, using = "outFlightName")
    WebElement selectedOutfLightName;

    @FindBy(how = How.NAME, using = "outFlightNumber")
    WebElement selectedOutfLightNumber;

    @FindBy(how = How.NAME, using = "inFlightPrice")
    WebElement inFlightPrice;

    @FindBy(how = How.NAME, using = "outFlightPrice")
    WebElement outFlightPrice;

    @FindBy(how = How.NAME, using = "passCount")
    WebElement passangerCount;

    @FindBy(how = How.NAME, using = "subtotal")
    WebElement subtotal;

    @FindBy(how = How.XPATH, using = "//font/b[contains(text(), '$')]")
    WebElement total;

    @FindBy(how = How.NAME, using = "taxes")
    WebElement taxes;

    @FindBy(how = How.NAME, using = "passFirst0")
    WebElement userFirstName;

    @FindBy(how = How.NAME, using = "passLast0")
    WebElement userLastName;

    @FindBy(how = How.NAME, using = "creditCard")
    WebElement creditCardType;

    @FindBy(how = How.NAME, using = "creditnumber")
    WebElement creditCardNumber;

    @FindBy(how = How.NAME, using = "cc_exp_dt_mn")
    WebElement expiryMonth;

    @FindBy(how = How.NAME, using = "cc_exp_dt_yr")
    WebElement expiryYear;

    @FindBy(how = How.NAME, using = "cc_frst_name")
    WebElement ccFirstName;

    @FindBy(how = How.NAME, using = "cc_last_name")
    WebElement ccLastName;

    @FindBy(how = How.NAME, using = "billAddress1")
    WebElement billAddress1;

    @FindBy(how = How.NAME, using = "billAddress2")
    WebElement billAddress2;

    @FindBy(how = How.NAME, using = "billCity")
    WebElement billCity;

    @FindBy(how = How.NAME, using = "billState")
    WebElement billState;

    @FindBy(how = How.NAME, using = "billZip")
    WebElement billZip;

    @FindBy(how = How.NAME, using = "billCountry")
    WebElement billCountry;

    @FindBy(how = How.NAME, using = "ticketLess")
    WebElement sameAsBillingAddress;

    @FindBy(how = How.NAME, using = "buyFlights")
    WebElement buyFlights;

    public BookFlightPage getInstance(WebDriver driver) {
        this.driver = driver;
        return PageFactory.initElements(driver, BookFlightPage.class);
    }

    public Boolean isInboundFlightCorrect(String source) {
        return (selectedInfLightName.getAttribute("value")+" "+selectedInfLightNumber.getAttribute("value")).equalsIgnoreCase(source);
    }

    public Boolean isOutboundFlightCorrect(String dest) {
        return (selectedOutfLightName.getAttribute("value")+" "+selectedOutfLightNumber.getAttribute("value")).equalsIgnoreCase(dest);
    }

    public Boolean isTotalCorrect() {
        Float calculatedTotal =  convertToF(passangerCount) * (convertToF(inFlightPrice) + convertToF(outFlightPrice)) + convertToF(taxes);
        Float actualTotal = Float.parseFloat(total.getText().substring(1));
        return  calculatedTotal.equals(actualTotal);
    }

    Float convertToF(WebElement price) {
        return Float.parseFloat(price.getAttribute("value"));
    }

    public void bookFlight(Properties props) {
        ObjectMapper mapper = new ObjectMapper();
        UserInfo info = new UserInfo();
        try {
            info = mapper.readValue(props.getProperty("userInfo"), UserInfo.class);
            userFirstName.sendKeys(info.getFirstName());
            userLastName.sendKeys(info.getLastName());
            new Select(creditCardType).selectByVisibleText(info.getCardType());
            creditCardNumber.sendKeys(info.getCardNumber());
            new Select(expiryMonth).selectByVisibleText(info.getExpirationMonth());
            new Select(expiryYear).selectByValue(info.getExpirationYear());
            ccFirstName.sendKeys(info.getFirstName());
            ccLastName.sendKeys(info.getLastName());
            billAddress1.clear();
            billAddress1.sendKeys(info.getStreetAddressOne());
            billAddress2.sendKeys(info.getStreetAddressTwo());
            billCity.clear();
            billCity.sendKeys(info.getCity());
            billState.clear();
            billState.sendKeys(info.getState());
            billZip.clear();
            billZip.sendKeys(info.getPostalCode());
            new Select(billCountry).selectByVisibleText(info.getCountry());
            buyFlights.click();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
