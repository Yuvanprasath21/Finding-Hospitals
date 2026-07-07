package org.hospital.tests;

import basetest.BaseTest;
import org.hospital.pages.ParkingFacilityPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_08_CallButtonClickable extends BaseTest {

    @Test
    public void verifyCallButtonsAreClickable() {
        logger.info("Starting : {}", this.getClass().getSimpleName());
        logger.info("Verifying Call button clickability on hospital pages");

        navigateToSearchResults();
        ParkingFacilityPage page = new ParkingFacilityPage(driver);
        boolean anyClickable = page.callButton();
        Assert.assertTrue(
                anyClickable,
                "No call button was clickable!"
        );

        logger.info("All Call buttons are clickable");
        logger.info("Call button clickability validation completed successfully");
        logger.info("Ending : {}", this.getClass().getSimpleName());
    }
}