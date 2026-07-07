package org.hospital.tests;

import basetest.BaseTest;
import org.hospital.pages.SearchResultPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

@Test
public class TC_03_Open24x7Hospitals extends BaseTest {
    public void verify_timing(){
        logger.info("Starting : {}", this.getClass().getSimpleName());
        logger.info("Retrieving hospitals operating 24x7");

        navigateToSearchResults();
        SearchResultPage searchResultPage=new SearchResultPage(driver);
        Set<String> hospitals = searchResultPage.get24x7Hospitals();
        Assert.assertFalse(
                hospitals.isEmpty(),
                "24x7 Open Hospitals list is empty"
        );
        logger.info("Found {} hospitals operating 24x7", hospitals.size());
        Assert.assertTrue(
                hospitals.size() >= 10,
                "Hospitals with 24x7Open is less than 10"
        );
        logger.info("24x7 hospitals validation completed successfully");
        logger.info("Hospitals operating 24x7: {}", hospitals);
        logger.info("Ending : {}", this.getClass().getSimpleName());
    }
}
