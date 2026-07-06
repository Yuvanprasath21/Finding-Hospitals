package org.hospital.tests;

import basetest.BaseTest;
import org.hospital.pages.HomePage;
import org.hospital.pages.SearchResultPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;

@Test
public class TC_02_HospitalCount extends BaseTest {
    public void verify_data() {
        logger.info("Starting : " + this.getClass().getSimpleName());
        logger.info("Verifying hospital count availability");

        navigateToSearchResults();

        SearchResultPage searchResultPage = new SearchResultPage(driver);
        boolean isVisible = searchResultPage.isElementVisible();
        Assert.assertTrue(
                isVisible,
                "Hospital Count is not displayed"
        );
        logger.info("Hospital count element is displayed");
        int count = Integer.parseInt(
                searchResultPage.countOfHospitals()
        );
        logger.info("Retrieved hospital count: {}", count);
        Assert.assertTrue(
                count > 0,
                "Hospital count is zero. Result is empty."
        );
        logger.info("Hospital count validation completed successfully");
        logger.info("Ending : " + this.getClass().getSimpleName());
    }
}
