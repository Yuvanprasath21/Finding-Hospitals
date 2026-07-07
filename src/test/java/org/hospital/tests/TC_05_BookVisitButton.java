package org.hospital.tests;

import basetest.BaseTest;
import org.hospital.pages.SearchResultPage;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TC_05_BookVisitButton extends BaseTest {
    public void verify_button() {
        logger.info("Starting : {}", this.getClass().getSimpleName());
        logger.info("Verifying Book Visit button clickability");

        navigateToSearchResults();
        SearchResultPage searchResultPage = new SearchResultPage(driver);
        boolean isClickable = searchResultPage.areButtonsClickable();
        Assert.assertTrue(
                isClickable,
                "Buttons are not clickable"
        );
        logger.info("All Book Visit buttons are clickable");
        logger.info("Button clickability validation completed successfully");
        logger.info("Ending : {}", this.getClass().getSimpleName());
    }
}
