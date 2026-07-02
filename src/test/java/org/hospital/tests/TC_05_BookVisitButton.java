package org.hospital.tests;

import basetest.BaseTest;
import org.hospital.pages.SearchResultPage;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TC_05_BookVisitButton extends BaseTest {
    public void verify_button(){
        SearchResultPage searchResultPage = new SearchResultPage(driver);
        boolean isClickable = searchResultPage.areButtonsClickable();
        Assert.assertTrue(
                isClickable,
                "Buttons are not clickable"
        );
        System.out.println("Are all buttons clickable: "+isClickable);
    }
}
