package com.exercise.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ConfirmationPage {
    WebDriver driver;

    @FindBy(how = How.XPATH, using = "//td[@class='frame_header_info']")
    List<WebElement> flightDetails;

    @FindBy(how = How.XPATH, using = "//a[text()='SIGN-OFF']")
    WebElement signOff;

    public ConfirmationPage getInstance(WebDriver driver) {
        this.driver = driver;
        return PageFactory.initElements(driver, ConfirmationPage.class);
    }

    public  void signMeOff() {
        signOff.click();
    }

    public String getInboundFlightName() {
        return flightDetails.get(4).getText().split("\n")[1].split("w/")[1].trim();
    }
    public  String getOutboundFlightName() {
        return flightDetails.get(2).getText().split("\n")[1].split("w/")[1].trim();
    }

    public String getConfirmationNumber() {
        return flightDetails.get(0).getText().split("# ")[1].split(" ")[0];
    }
}
