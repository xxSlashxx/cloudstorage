package com.udacity.jwdnd.course1.cloudstorage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SignupTest extends BaseTest {

    @Test
    public void testSignup() {
        // Try to access home without login.
        driver.get("http://localhost:" + this.port + "/home");

        // Check if we have been redirected to the log in page.
        Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());
    }
}