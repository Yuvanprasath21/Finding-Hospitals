package org.hospital.tests;

import basetest.BaseTest;
import org.hospital.pages.ParkingFacilityPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_07_ParkingAmenities extends BaseTest {

    @Test
    public void verifyParkingAmenity() {
        logger.info("Starting : {}", this.getClass().getSimpleName());
        logger.info("Verifying parking amenity availability across hospitals");

        navigateToSearchResults();
        ParkingFacilityPage page = new ParkingFacilityPage(driver);
        int totalWindows = page.navigateAndHandleHospital();
        logger.info("Total windows opened: {}", totalWindows);
        int hospitalsWithParking = page.checkingAmenities();
        logger.info("Hospitals with parking: {}", hospitalsWithParking);
        Assert.assertTrue(
                hospitalsWithParking > 0,
                "Expected at least one hospital with parking, but found none!"
        );
        logger.info("Parking amenity check completed for all hospitals");
        logger.info("Parking amenity validation completed successfully");
        logger.info("Ending : {}", this.getClass().getSimpleName());
    }
}