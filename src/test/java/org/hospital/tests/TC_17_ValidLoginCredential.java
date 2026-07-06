package org.hospital.tests;

import basetest.BaseTest;
import org.hospital.pages.DoctorsLoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ExcelUtils;

public class TC_17_ValidLoginCredential extends BaseTest {

    @Test
    public void verifyValidLogin() {

        logger.info("**** Starting TC_17_ValidLoginCredential ****");

        try {

            String[] loginData = ExcelUtils.getLoginData(1);

            DoctorsLoginPage loginPage =
                    new DoctorsLoginPage(driver);

            loginPage.enterEmail(loginData[0]);
            loginPage.enterPassword(loginData[1]);
            loginPage.clickLogin();

            logger.info("Entered valid credentials");

            Assert.assertTrue(
                    driver.getTitle().contains("Practo"),
                    "Login failed"
            );

            logger.info("Valid Login Successful");

        } catch (Exception e) {

            logger.error("TC_17 failed", e);
            Assert.fail(e.getMessage());
        }
    }
}
