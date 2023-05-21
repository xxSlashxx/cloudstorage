package com.udacity.jwdnd.course1.cloudstorage.components;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    @FindBy(id = "nav-notes-tab")
    private WebElement notesTab;

    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialsTab;

    @FindBy(id = "addNoteButton")
    private WebElement addNoteButton;

    @FindBy(id = "addCredentialButton")
    private WebElement addCredentialButton;

    @FindBy(id = "userTable")
    private WebElement notesTable;

    @FindBy(css = "table>tbody tr:last-child td:first-child button")
    private WebElement lastEditNoteButton;

    @FindBy(css = "table>tbody tr:last-child td:first-child a")
    private WebElement lastDeleteNoteLink;

    @FindBy(css = "table>tbody tr:last-child th")
    private WebElement lastNoteTitle;

    @FindBy(css = "table>tbody tr:last-child td:last-child")
    private WebElement lastNoteDescription;

    @FindBy(css = "table>tbody tr:last-child th")
    private WebElement lastCredentialUrl;

    @FindBy(css = "table>tbody tr:last-child td:nth-of-type(2)")
    private WebElement lastCredentialUsername;

    @FindBy(css = "table>tbody tr:last-child td:last-child")
    private WebElement lastCredentialPassword;

    @FindBy(css = "table>tbody tr:last-child td:first-child button")
    private WebElement lastEditCredentialButton;

    @FindBy(css = "table>tbody tr:last-child td:first-child a")
    private WebElement lastDeleteCredentialLink;

    private final WebDriver driver;

    private final WebDriverWait webDriverWait;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        webDriverWait = new WebDriverWait(driver, 1);
    }

    public void clickNotesTab() {
        notesTab.click();
    }

    public void clickCredentialsTab() {
        credentialsTab.click();
    }

    public void clickAddNoteButton() {
        clickNotesTab();
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(addNoteButton));
        addNoteButton.click();
    }

    public void clickAddCredentialButton() {
        clickCredentialsTab();
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(addCredentialButton));
        addCredentialButton.click();
    }

    public void clickLastEditNoteButton() {
        clickNotesTab();
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(lastEditNoteButton));
        lastEditNoteButton.click();
    }

    public void clickLastEditCredentialButton() {
        clickCredentialsTab();
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(lastEditCredentialButton));
        lastEditCredentialButton.click();
    }

    public void clickLastDeleteNoteButton() {
        clickNotesTab();
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(lastDeleteNoteLink));
        lastDeleteNoteLink.click();
    }

    public void clickLastDeleteCredentialButton() {
        clickCredentialsTab();
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(lastDeleteCredentialLink));
        lastDeleteCredentialLink.click();
    }

    public Note getLastNote() {
        clickNotesTab();
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(lastNoteTitle, lastNoteDescription));
        return new Note(null, lastNoteTitle.getText(), lastNoteDescription.getText(), null);
    }

    public Credential getLastCredential() {
        clickCredentialsTab();
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(lastCredentialUrl, lastCredentialUsername, lastCredentialPassword));
        return new Credential(null, lastCredentialUrl.getText(), lastCredentialUsername.getText(), null, lastCredentialPassword.getText(), null);
    }

    public boolean isNoteExisting() {
        clickNotesTab();
        return !driver.findElements(By.cssSelector("table>tbody tr:last-child th")).isEmpty() && !driver.findElements(By.cssSelector("table>tbody tr:last-child td:last-child")).isEmpty();
    }

    public boolean isCredentialExisting() {
        clickCredentialsTab();
        return !driver.findElements(By.cssSelector("table>tbody tr:last-child th")).isEmpty() && !driver.findElements(By.cssSelector("table>tbody tr:last-child td:last-child")).isEmpty();
    }
}