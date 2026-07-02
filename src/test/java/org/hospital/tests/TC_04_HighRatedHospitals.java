package org.hospital.tests;

import basetest.BaseTest;
import org.hospital.pages.SearchResultPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

@Test
public class TC_04_HighRatedHospitals extends BaseTest {
    public void verify_rating() {
        SearchResultPage searchResultPage = new SearchResultPage(driver);
        Set<String> hospitals = searchResultPage.getHighRatingHospitals();
        Assert.assertFalse(
                hospitals.isEmpty(),
                "High Rated Hospitals list is empty"
        );
        Assert.assertTrue(
                hospitals.size()>=10,
                "Hospitals with rating > 3.5 is less than 10");
        System.out.println("Hospitals which has rating over 3.5\n"+hospitals);
    }
}
