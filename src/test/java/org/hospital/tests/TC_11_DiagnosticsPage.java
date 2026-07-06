package org.hospital.tests;

import java.util.List;
import org.hospital.pages.DiagnosticsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import basetest.BaseTest;

public class TC_11_DiagnosticsPage extends BaseTest {

    @Test
    public void verifyUserCanNavigateToDiagnosticsPage() {

        DiagnosticsPage diagnostics = new DiagnosticsPage(driver);
        diagnostics.clickDiagnostics();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("tests"), "User did NOT navigate to Diagnostics page");
        System.out.println("User successfully navigated to Diagnostics page");
        System.out.println("Heading 1 : " + diagnostics.getTopCitgiesHeading());
        System.out.println("Heading 2 : " + diagnostics.getAllCitiesHeading());
        driver.get(properties.getProperty("website_url"));

        diagnostics.clickFindDoctorsNearYou();
        Assert.assertTrue(driver.getCurrentUrl().contains("doctors"), "User did NOT navigate to Find Doctors page");
        System.out.println("\nUser successfully navigated to Find Doctors page");

        List<String> searches = diagnostics.getPopularSearches();
        System.out.println("Popular Searches:");
        for (String search : searches) {
            System.out.println(search);
        }
    }
}