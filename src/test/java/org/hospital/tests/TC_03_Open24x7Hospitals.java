package org.hospital.tests;

import basetest.BaseTest;
import org.hospital.pages.SearchResultPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

@Test
public class TC_03_Open24x7Hospitals extends BaseTest {
    public void verify_timing(){
        SearchResultPage searchResultPage=new SearchResultPage(driver);
        Set<String> hospitals = searchResultPage.get24x7Hospitals();
        Assert.assertFalse(
                hospitals.isEmpty(),
                "24x7 Open Hospitals list is empty"
        );
        Assert.assertTrue(
                hospitals.size()>=10,
                "Hospitals with 24x7 is less than 10");
        System.out.println("Hospitals which is open 24x7:\n"+hospitals);
    }
}
