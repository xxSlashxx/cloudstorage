package com.udacity.jwdnd.course1.cloudstorage.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage {

    @FindBy(id = "success")
    private WebElement successResultHeader;

    @FindBy(id = "home-link")
    private WebElement homeLink;

    public ResultPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String getResultHeader() {
        return successResultHeader.getText();
    }

    public void clickHomeLink() {
        homeLink.click();
    }
}