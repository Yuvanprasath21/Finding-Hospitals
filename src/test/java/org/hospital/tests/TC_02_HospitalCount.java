package org.hospital.tests;

import basetest.BaseTest;
import org.hospital.pages.SearchResultPage;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TC_02_HospitalCount extends BaseTest {
    public void verify_data() {
        SearchResultPage searchResultPage=new SearchResultPage(driver);
        boolean isVisible = searchResultPage.isElementVisible();
        Assert.assertTrue(
                isVisible,
                "Hospital Count is not displayed"
        );
        int count = Integer.parseInt(
                searchResultPage.countOfHospitals()
        );
        Assert.assertTrue(
                count>0,
                "Hospital count is zero. Result is empty."
        );
        System.out.println("Count of Hospitals: "+count);
    }
}
