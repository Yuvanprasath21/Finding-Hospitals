package org.hospital.tests;

import basetest.BaseTest;
import org.hospital.pages.DiagnosticsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class TC_14_AddSecondTestToCart extends BaseTest {

    @Test
    public void verifyUserCanAddSecondTestToCart() {
        logger.info("Starting : {}", this.getClass().getSimpleName());
        SoftAssert softAssert = new SoftAssert();
        DiagnosticsPage diagnostics = new DiagnosticsPage(driver);

        logger.info("Clicking Diagnostics menu");
        diagnostics.clickDiagnostics();
        softAssert.assertTrue(
                diagnostics.isDiagnosticsPageOpened(),
                "Diagnostics page did not open"
        );

        logger.info("Selecting Bangalore city");
        diagnostics.selectBangalore();
        softAssert.assertTrue(
                diagnostics.isSecondTestCardVisible(),
                "Second test card is not visible"
        );

        logger.info("Opening second test card");
        diagnostics.openSecondTestCard();
        softAssert.assertTrue(
                diagnostics.isAddToCartButtonVisible(),
                "Add To Cart button is not visible"
        );

        logger.info("Adding second test to cart");
        diagnostics.clickAddToCart();
        softAssert.assertFalse(
                diagnostics.isCartPageOpened(),
                "User was redirected to cart page"
        );

        logger.info("Second test added to cart successfully");
        softAssert.assertAll();
        logger.info("Ending : {}", this.getClass().getSimpleName());
    }
}