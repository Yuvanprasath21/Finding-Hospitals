package org.hospital.tests;

import basetest.BaseTest;
import org.hospital.pages.DoctorsLoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ExcelUtils;

public class TC_20_WrongCredential extends BaseTest {

    @Test
    public void verifyWrongCredentialLogin() {

        logger.info("**** Starting TC_20_WrongCredential ****");

        try {

            // Read wrong username and password from Excel
            String[] loginData = ExcelUtils.getLoginData(4);

            DoctorsLoginPage loginPage =
                    new DoctorsLoginPage(driver);

            loginPage.enterEmail(loginData[0]);
            loginPage.enterPassword(loginData[1]);
            loginPage.clickLogin();

            logger.info("Entered wrong credentials");

            Thread.sleep(3000);

            // Verify login failed
            Assert.assertTrue(
                    loginPage.isLoginButtonDisplayed(),
                    "User logged in with wrong credentials"
            );

            logger.info("Login failed as expected");

        } catch (Exception e) {

            logger.error("TC_20 failed", e);
            Assert.fail(e.getMessage());
        }
    }
}