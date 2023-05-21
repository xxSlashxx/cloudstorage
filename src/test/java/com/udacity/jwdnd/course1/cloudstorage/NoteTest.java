package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.components.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.components.NoteDialog;
import com.udacity.jwdnd.course1.cloudstorage.components.ResultPage;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NoteTest extends BaseTest {

    @Test
    public void testAddNote() {
        doMockSignUp("Elon", "Musk", "testAddNote", "123");
        doLogIn("testAddNote", "123");

        HomePage homePage = new HomePage(driver);
        homePage.clickAddNoteButton();

        NoteDialog noteDialog = new NoteDialog(driver);
        noteDialog.submitNote("MyAddedNoteTitle", "MyAddedNoteDescription");

        ResultPage resultPage = new ResultPage(driver);
        Assertions.assertEquals("Success", resultPage.getResultHeader());

        resultPage.clickHomeLink();
        Assertions.assertEquals("http://localhost:" + this.port + "/home", driver.getCurrentUrl());

        homePage.clickNotesTab();
        Note lastNote = homePage.getLastNote();

        Assertions.assertEquals("MyAddedNoteTitle", lastNote.getNotetitle());
        Assertions.assertEquals("MyAddedNoteDescription", lastNote.getNotedescription());
    }

    @Test
    public void testEditNote() {
        doMockSignUp("Elon", "Musk", "testEditNote", "123");
        doLogIn("testEditNote", "123");

        HomePage homePage = new HomePage(driver);
        homePage.clickAddNoteButton();

        NoteDialog noteDialog = new NoteDialog(driver);
        noteDialog.submitNote("MyNoteTitle", "MyNoteDescription");

        ResultPage resultPage = new ResultPage(driver);
        resultPage.clickHomeLink();

        homePage.clickLastEditNoteButton();

        Note noteToEdit = noteDialog.getNote();
        Assertions.assertEquals("MyNoteTitle", noteToEdit.getNotetitle());
        Assertions.assertEquals("MyNoteDescription", noteToEdit.getNotedescription());
        noteDialog.submitNote("_edited_1", "_edited_2");

        resultPage.clickHomeLink();

        homePage.clickNotesTab();
        Note lastNote = homePage.getLastNote();
        Assertions.assertEquals("MyNoteTitle_edited_1", lastNote.getNotetitle());
        Assertions.assertEquals("MyNoteDescription_edited_2", lastNote.getNotedescription());
    }

    @Test
    public void testDeleteNote() {
        doMockSignUp("Elon", "Musk", "testDeleteNote", "123");
        doLogIn("testDeleteNote", "123");

        HomePage homePage = new HomePage(driver);
        homePage.clickAddNoteButton();

        NoteDialog noteDialog = new NoteDialog(driver);
        noteDialog.submitNote("MyNoteTitle", "MyNoteDescription");

        ResultPage resultPage = new ResultPage(driver);
        resultPage.clickHomeLink();

        homePage.clickLastDeleteNoteButton();

        Assertions.assertEquals("Success", resultPage.getResultHeader());
        resultPage.clickHomeLink();

        Assertions.assertFalse(homePage.isNoteExisting());
    }
}