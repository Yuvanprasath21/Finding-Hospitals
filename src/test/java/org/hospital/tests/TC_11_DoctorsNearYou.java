package org.hospital.tests;

import basetest.BaseTest;
import org.hospital.pages.DiagnosticsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;

import java.util.List;

public class TC_11_DoctorsNearYou extends BaseTest {

    @Test
    public void verifyUserCanNavigateToDiagnosticsPage() {
        logger.info("Starting : {}", this.getClass().getSimpleName());
        DiagnosticsPage diagnostics = new DiagnosticsPage(driver);

        logger.info("Clicking Find Doctors Near You");
        diagnostics.clickFindDoctorsNearYou();
        Assert.assertTrue(
                diagnostics.isDoctorsPageOpened(),
                "User did NOT navigate to Find Doctors page"
        );

        List<String> searches = diagnostics.getPopularSearches();
        Assert.assertFalse(
                searches.isEmpty(),
                "Popular Searches are not displayed"
        );
        logger.info("Popular Searches: {}", searches);

        logger.info("Diagnostics Page test completed successfully");
        logger.info("Ending : {}", this.getClass().getSimpleName());
    }
}