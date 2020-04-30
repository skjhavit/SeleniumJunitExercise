package com.exercise.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class GoogleHomePage {
    WebDriver driver;

    @FindBy(how = How.NAME, using = "q")
    WebElement searchTextBox;

    @FindBy(how = How.NAME, using = "btnK")
    WebElement searchButton;

    public void enterSearchText(String searchText) {
        this.searchTextBox.sendKeys(searchText);
    }

    public void clickSearchButton() {
        this.searchTextBox.submit();
    }

    public GoogleHomePage getInstance(WebDriver driver) {
        this.driver = driver;
        return PageFactory.initElements(driver, GoogleHomePage.class);
    }
}
