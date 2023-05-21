package com.udacity.jwdnd.course1.cloudstorage.components;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NoteDialog {

    @FindBy(id = "note-title")
    private WebElement inputNoteTitle;

    @FindBy(id = "note-description")
    private WebElement inputNoteDescription;

    @FindBy(id = "submitNoteButton")
    private WebElement submitNoteButton;

    private final WebDriverWait webDriverWait;

    public NoteDialog(WebDriver driver) {
        PageFactory.initElements(driver, this);
        webDriverWait = new WebDriverWait(driver, 1);
    }

    public void submitNote(String noteTitle, String noteDescription) {
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(inputNoteTitle, inputNoteDescription, submitNoteButton));
        inputNoteTitle.click();
        inputNoteTitle.sendKeys(noteTitle);
        inputNoteDescription.click();
        inputNoteDescription.sendKeys(noteDescription);
        submitNoteButton.click();
    }

    public Note getNote() {
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(inputNoteTitle, inputNoteDescription));
        return new Note(null, inputNoteTitle.getAttribute("value"), inputNoteDescription.getAttribute("value"), null);
    }
}