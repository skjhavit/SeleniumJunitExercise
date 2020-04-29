package com.exercise.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ToursHomePage {
    WebDriver driver;

    @FindBy(how = How.XPATH, using = "//form[@name='home']/table/tbody/tr[1]/td[1]/font/b")
    WebElement currentDate;

    @FindBy(how = How.XPATH, using = "//img[@src='/images/featured_destination.gif']")
    WebElement featuredDestination;

    @FindBy(how = How.NAME, using = "userName")
    WebElement userName;

    @FindBy(how = How.NAME, using = "password")
    WebElement password;

    @FindBy(how = How.NAME, using = "login")
    WebElement signIn;

    public ToursHomePage getInstance(WebDriver driver) {
        this.driver = driver;
        return PageFactory.initElements(driver, ToursHomePage.class);
    }

    public String getCurrentDate() {
        return this.currentDate.getText();
    }

    public String getFeaturedDestination() {
        return this.featuredDestination.getAttribute("alt");
    }

    public void typeUserName(String userName) {
        this.userName.sendKeys(userName);
    }

    public void typePassword(String password) {
        this.password.sendKeys(password);
    }

    public void submitForm() {
        this.signIn.click();
    }
}
