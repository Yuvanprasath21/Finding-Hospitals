package org.hospital.tests;

import basetest.BaseTest;
import org.hospital.pages.ParkingFacilityPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TC_09_PhoneNumbers extends BaseTest {

    @Test
    public void capturePhoneNumbers() {
        logger.info("Starting : {}", this.getClass().getSimpleName());
        logger.info("Verifying phone numbers are captured after revealing them");

        navigateToSearchResults();
        ParkingFacilityPage page = new ParkingFacilityPage(driver);
        List<String> phones = page.getPhoneNumbers();
        logger.info("Captured phone numbers: {}", phones);
        Assert.assertFalse(
                phones.isEmpty(),
                "No phone numbers captured!"
        );
        logger.info("All phone numbers captured successfully");
        logger.info("Phone number capture validation completed successfully");
        logger.info("Ending : {}", this.getClass().getSimpleName());
    }
}