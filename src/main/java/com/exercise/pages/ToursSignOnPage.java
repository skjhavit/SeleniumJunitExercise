package com.exercise.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ToursSignOnPage {
    WebDriver driver;
    @FindBy(how = How.XPATH, using ="//img[@src='/images/masts/mast_signon.gif']")
    WebElement signOnImage;
    @FindBy(how = How.XPATH, using = "//*[contains(text(),'Enter your user information')]")
    WebElement signOnSection;
    @FindBy(how = How.NAME, using = "userName")
    WebElement userName;
    @FindBy(how = How.NAME, using = "password")
    WebElement password;
    @FindBy(how = How.NAME, using = "login")
    WebElement submit;

    public ToursSignOnPage getInstance(WebDriver driver) {
        this.driver = driver;
        return PageFactory.initElements(driver, ToursSignOnPage.class);
    }

    public String getSignOnImage() { return this.signOnImage.getAttribute("src");}
    public String getSignOnSectionText() {return this.signOnSection.getText();}
    public void typeUserName(String userName) {
        this.userName.sendKeys(userName);
    }
    public void typePassword(String password) {
        this.password.sendKeys(password);
    }
    public void submitForm() {
        this.submit.click();
    }
}
