package org.hospital.tests;

import basetest.BaseTest;
import org.hospital.pages.SearchResultPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

@Test
public class TC_04_HighRatedHospitals extends BaseTest {
    public void verify_rating() {
        logger.info("Retrieving hospitals with rating greater than 3.5");
        SearchResultPage searchResultPage = new SearchResultPage(driver);
        Set<String> hospitals = searchResultPage.getHighRatingHospitals();
        Assert.assertFalse(
                hospitals.isEmpty(),
                "High Rated Hospitals list is empty"
        );
        logger.info("Found {} hospitals with rating greater than 3.5", hospitals.size());
        Assert.assertTrue(
                hospitals.size() >= 10,
                "Hospitals with rating > 3.5 is less than 10"
        );
        logger.info("High-rated hospitals validation completed successfully");
        logger.info("Hospitals with rating greater than 3.5: {}", hospitals);
    }
}
