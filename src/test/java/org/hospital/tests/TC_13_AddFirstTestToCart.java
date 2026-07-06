package org.hospital.tests;

import java.time.Duration;
import org.hospital.pages.DiagnosticsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import basetest.BaseTest;

public class TC_13_AddFirstTestToCart extends BaseTest {

    @Test
    public void verifyUserCanAddFirstTestToCart() {

        SoftAssert softAssert = new SoftAssert();
        DiagnosticsPage diagnostics = new DiagnosticsPage(driver);

        FluentWait<WebDriver> slowWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(2));
        diagnostics.clickDiagnostics();
        slowWait.until(ExpectedConditions.urlContains("tests"));
        softAssert.assertTrue(
                driver.getCurrentUrl().contains("tests"),
                "Diagnostics page did not open"
        );

        diagnostics.selectBangalore();
        slowWait.until(ExpectedConditions.visibilityOf(
                diagnostics.getFirstTestCardElement()
        ));
        softAssert.assertTrue(diagnostics.getFirstTestCardElement().isDisplayed(), "First test card is not visible");

        diagnostics.clickFirstTestCard();
        slowWait.until(ExpectedConditions.urlContains("thyroid"));
        softAssert.assertTrue(driver.getCurrentUrl().contains("thyroid"), "User is not navigated to the test details page"
        );

        System.out.println("Test details page opened.");
        slowWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[normalize-space()='Add to Cart']")
        ));

        diagnostics.clickAddToCart();
        softAssert.assertTrue(driver.getCurrentUrl().contains("thyroid"), "User left the test details page unexpectedly");
        System.out.println("First test added to cart successfully.\n");
        softAssert.assertAll();
    }
}