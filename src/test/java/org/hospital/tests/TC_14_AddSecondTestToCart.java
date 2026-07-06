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

public class TC_14_AddSecondTestToCart extends BaseTest {

    @Test
    public void verifyUserCanAddSecondTestToCart() {

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
                diagnostics.getSecondTestCardElement()
        ));
        softAssert.assertTrue(
                diagnostics.getSecondTestCardElement().isDisplayed(),
                "Second test card is not visible"
        );

        diagnostics.clickSecondTestCard();
        slowWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[normalize-space()='Add to Cart']")
        ));
        softAssert.assertTrue(
                diagnostics.getAddToCartButtonElement().isDisplayed(),
                "Add To Cart button is not visible"
        );
        System.out.println("Second test details page opened.");

        //Add to Cart
        diagnostics.clickAddToCart();
        System.out.println("Second test added to cart successfully.\n");
        softAssert.assertFalse(driver.getCurrentUrl().contains("cart"), "User was redirected to cart page");
        softAssert.assertAll();
    }
}
