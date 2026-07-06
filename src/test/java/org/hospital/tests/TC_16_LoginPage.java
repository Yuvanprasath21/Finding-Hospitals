package org.hospital.tests;

import basetest.BaseTest;
import org.hospital.pages.DoctorsLoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_16_LoginPage extends BaseTest {

    @Test
    public void verifyPractoLoginPageDisplayed() {

        logger.info("**** Starting TC_16_LoginPage ****");

        try {

            DoctorsLoginPage loginPage = new DoctorsLoginPage(driver);

            String actualTitle = driver.getTitle();

            logger.info("Actual Page Title: " + actualTitle);
            logger.info("Current URL: " + driver.getCurrentUrl());

            Assert.assertEquals(
                    actualTitle,
                    "Practo Accounts",
                    "Page title validation failed"
            );

            logger.info("Practo Login Page displayed successfully");

        } catch (Exception e) {

            logger.error("TC_16_LoginPage failed", e);
            Assert.fail("Test failed: " + e.getMessage());

        }

        logger.info("**** TC_16_LoginPage Completed ****");
    }
}