package org.hospital.tests;

import basetest.BaseTest;
import org.hospital.pages.DiagnosticsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC_13_AddFirstTestToCart extends BaseTest {

    @Test
    public void verifyUserCanAddFirstTestToCart() {
        logger.info("Starting : {}", this.getClass().getSimpleName());

        SoftAssert softAssert = new SoftAssert();
        DiagnosticsPage diagnostics = new DiagnosticsPage(driver);

        logger.info("Clicking Diagnostics menu");
        diagnostics.clickDiagnostics();
        Assert.assertTrue(
                diagnostics.isDiagnosticsPageOpened(),
                "User did NOT navigate to Diagnostics page"
        );
        logger.info("Selecting Bangalore city");
        diagnostics.selectBangalore();
        softAssert.assertTrue(
                diagnostics.isFirstTestCardVisible(),
                "First test card is not visible"
        );
        logger.info("Opening first test card");
        diagnostics.openFirstTestCard();
        softAssert.assertTrue(
                diagnostics.isTestDetailsPageOpened(),
                "User is not navigated to the test details page"
        );
        logger.info("Adding test to cart");
        diagnostics.addFirstTestToCart();
        softAssert.assertTrue(
                diagnostics.isTestDetailsPageOpened(),
                "User left the test details page unexpectedly"
        );
        logger.info("First test added to cart successfully");
        softAssert.assertAll();
        logger.info("Ending : {}", this.getClass().getSimpleName());
    }
}