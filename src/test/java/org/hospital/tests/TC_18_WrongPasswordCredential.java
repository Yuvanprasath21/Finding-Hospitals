package org.hospital.tests;

import basetest.BaseTest;
import org.hospital.pages.DoctorsLoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ExcelUtils;

public class TC_18_WrongPasswordCredential extends BaseTest {

    @Test
    public void verifyWrongPasswordLogin() {

        logger.info("**** Starting TC_18_WrongPasswordCredential ****");

        try {

            // Read wrong password data from Excel
            String[] loginData = ExcelUtils.getLoginData(2);

            DoctorsLoginPage loginPage =
                    new DoctorsLoginPage(driver);

            loginPage.enterEmail(loginData[0]);
            loginPage.enterPassword(loginData[1]);
            loginPage.clickLogin();

            logger.info("Entered invalid password");

            // Verify login failed
            loginPage.clickLogin();

            Thread.sleep(3000);

            Assert.assertTrue(
                    loginPage.isLoginButtonDisplayed(),
                    "User logged in with wrong password"
            );

            logger.info("Login failed as expected");

        } catch (Exception e) {

            logger.error("TC_18 failed", e);
            Assert.fail(e.getMessage());
        }
    }
}