package com.udacity.jwdnd.course1.cloudstorage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {

    @Test
    public void getLoginPage() {
        driver.get("http://localhost:" + this.port + "/login");
        Assertions.assertEquals("Login", driver.getTitle());
    }

    @Test
    public void testLoginFlow() {
        // Sign up a new user
        doMockSignUp("URL", "Test", "testLoginFlow", "123");
        // Log that user in
        doLogIn("testLoginFlow", "123");

        // Verify that they can access the home page
        driver.get("http://localhost:" + this.port + "/home");
        Assertions.assertEquals("http://localhost:" + this.port + "/home", driver.getCurrentUrl());

        // Log out
        WebElement logoutButton = driver.findElement(By.id("logout-button"));
        logoutButton.click();
        Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());

        // Verify that the home page is no longer accessible
        driver.get("http://localhost:" + this.port + "/home");
        Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());
    }
}