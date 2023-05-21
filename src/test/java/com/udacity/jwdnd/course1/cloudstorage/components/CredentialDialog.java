package com.udacity.jwdnd.course1.cloudstorage.components;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CredentialDialog {

    @FindBy(id = "credential-url")
    private WebElement inputCredentialUrl;

    @FindBy(id = "credential-username")
    private WebElement inputCredentialUsername;

    @FindBy(id = "credential-password")
    private WebElement inputCredentialPassword;

    @FindBy(id = "submitCredentialButton")
    private WebElement submitCredentialButton;

    private final WebDriverWait webDriverWait;

    public CredentialDialog(WebDriver driver) {
        PageFactory.initElements(driver, this);
        webDriverWait = new WebDriverWait(driver, 2);
    }

    public void submitCredential(String credentialUrl, String credentialUsername, String credentialPassword) {
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(inputCredentialUrl, inputCredentialUsername, inputCredentialPassword, submitCredentialButton));
        inputCredentialUrl.click();
        inputCredentialUrl.sendKeys(credentialUrl);
        inputCredentialUsername.click();
        inputCredentialUsername.sendKeys(credentialUsername);
        inputCredentialPassword.click();
        inputCredentialPassword.sendKeys(credentialPassword);
        submitCredentialButton.click();
    }

    public Credential getCredential() {
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(inputCredentialUrl, inputCredentialUsername, inputCredentialPassword));
        return new Credential(null, inputCredentialUrl.getAttribute("value"), inputCredentialUsername.getAttribute("value"), null, inputCredentialPassword.getAttribute("value"), null);
    }
}