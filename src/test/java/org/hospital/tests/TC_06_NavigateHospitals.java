package org.hospital.tests;

import basetest.BaseTest;
import org.hospital.pages.ParkingFacilityPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_06_NavigateHospitals extends BaseTest {
    @Test
    public void navigateHospitalsInNewTabs() {
        logger.info("Starting : {}", this.getClass().getSimpleName());
        logger.info("Verifying hospitals open in new tabs from search results");

        navigateToSearchResults();
        ParkingFacilityPage page = new ParkingFacilityPage(driver);
        int totalWindows = page.navigateAndHandleHospital();
        logger.info("Total windows opened: {}", totalWindows);
        Assert.assertTrue(
                totalWindows > 1,
                "No new tabs opened!"
        );
        logger.info("All hospital links opened successfully in new tabs");
        logger.info("New tab navigation validation completed successfully");
        logger.info("Ending : {}", this.getClass().getSimpleName());
    }
}