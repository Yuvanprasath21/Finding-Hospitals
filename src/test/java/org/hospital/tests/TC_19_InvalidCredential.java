package org.hospital.tests;

import basetest.BaseTest;
import org.hospital.pages.DoctorsLoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ExcelUtils;

public class TC_19_InvalidCredential extends BaseTest {

    @Test
    public void verifyInvalidLogin() {

        logger.info("**** Starting TC_19_InvalidCredential ****");

        try {

            // Read invalid credential data from Excel
            String[] loginData = ExcelUtils.getLoginData(3);

            DoctorsLoginPage loginPage =
                    new DoctorsLoginPage(driver);

            loginPage.enterEmail(loginData[0]);
            loginPage.enterPassword(loginData[1]);
            loginPage.clickLogin();

            logger.info("Entered invalid credentials");

            Thread.sleep(3000);

            // Verify login failed
            Assert.assertTrue(
                    loginPage.isLoginButtonDisplayed(),
                    "User logged in with invalid credentials"
            );

            logger.info("Login failed as expected");

        } catch (Exception e) {

            logger.error("TC_19 failed", e);
            Assert.fail(e.getMessage());
        }
    }
}