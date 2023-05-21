package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.components.CredentialDialog;
import com.udacity.jwdnd.course1.cloudstorage.components.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.components.ResultPage;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CredentialTest extends BaseTest {

    @Test
    public void testAddCredential() {
        doMockSignUp("Elon", "Musk", "testAddCredential", "123");
        doLogIn("testAddCredential", "123");

        HomePage homePage = new HomePage(driver);
        homePage.clickAddCredentialButton();

        CredentialDialog credentialDialog = new CredentialDialog(driver);
        credentialDialog.submitCredential("url", "username", "password");

        ResultPage resultPage = new ResultPage(driver);
        Assertions.assertEquals("Success", resultPage.getResultHeader());

        resultPage.clickHomeLink();
        Assertions.assertEquals("http://localhost:" + this.port + "/home", driver.getCurrentUrl());

        homePage.clickCredentialsTab();
        Credential lastCredential = homePage.getLastCredential();

        Assertions.assertEquals("url", lastCredential.getUrl());
        Assertions.assertEquals("username", lastCredential.getUsername());
        Assertions.assertNotEquals("password", lastCredential.getPassword());
    }

    @Test
    public void testEditCredential() {
        doMockSignUp("Elon", "Musk", "testEditCredential", "123");
        doLogIn("testEditCredential", "123");

        HomePage homePage = new HomePage(driver);
        homePage.clickAddCredentialButton();

        CredentialDialog credentialDialog = new CredentialDialog(driver);
        credentialDialog.submitCredential("url", "username", "password");

        ResultPage resultPage = new ResultPage(driver);
        resultPage.clickHomeLink();

        homePage.clickCredentialsTab();
        homePage.clickLastEditCredentialButton();

        Credential credentialToEdit = credentialDialog.getCredential();
        Assertions.assertEquals("url", credentialToEdit.getUrl());
        Assertions.assertEquals("username", credentialToEdit.getUsername());
        Assertions.assertEquals("password", credentialToEdit.getPassword());
        credentialDialog.submitCredential("_edited_1", "_edited_2", "_edited_3");

        resultPage.clickHomeLink();

        homePage.clickCredentialsTab();
        Credential lastCredential = homePage.getLastCredential();
        Assertions.assertEquals("url_edited_1", lastCredential.getUrl());
        Assertions.assertEquals("username_edited_2", lastCredential.getUsername());
        Assertions.assertNotEquals("password_edited_3", lastCredential.getPassword());
    }

    @Test
    public void testDeleteCredential() {
        doMockSignUp("Elon", "Musk", "testDeleteCredential", "123");
        doLogIn("testDeleteCredential", "123");

        HomePage homePage = new HomePage(driver);
        homePage.clickAddCredentialButton();

        CredentialDialog credentialDialog = new CredentialDialog(driver);
        credentialDialog.submitCredential("url", "username", "password");

        ResultPage resultPage = new ResultPage(driver);
        resultPage.clickHomeLink();

        homePage.clickLastDeleteCredentialButton();

        Assertions.assertEquals("Success", resultPage.getResultHeader());
        resultPage.clickHomeLink();

        Assertions.assertFalse(homePage.isCredentialExisting());
    }
}