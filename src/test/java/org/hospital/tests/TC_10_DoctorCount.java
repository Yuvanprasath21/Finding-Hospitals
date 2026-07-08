package org.hospital.tests;

import basetest.BaseTest;
import org.hospital.pages.ParkingFacilityPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_10_DoctorCount extends BaseTest {

    @Test
    public void verifyDoctorCount() {
        logger.info("Starting : {}", this.getClass().getSimpleName());
        logger.info("Verifying displayed doctor count matches expected value");

        navigateToSearchResults();
        ParkingFacilityPage page = new ParkingFacilityPage(driver);
        boolean result = page.checkingDoctor();
        Assert.assertTrue(
                result,
                "Doctor count is not displayed for one or more hospitals!"
        );

        logger.info("Doctor count matches for all hospitals");
        logger.info("Doctor count validation completed successfully");
        logger.info("Ending : {}", this.getClass().getSimpleName());
    }
}