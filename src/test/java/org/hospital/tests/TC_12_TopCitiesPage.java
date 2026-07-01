package org.hospital.tests;

import org.hospital.pages.DiagnosticsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import basetest.BaseTest;

public class TC_12_TopCitiesPage extends BaseTest {

    @Test
    public void verifyTopCitiesSectionIsVisible() {

        DiagnosticsPage diagnostics = new DiagnosticsPage(driver);

        diagnostics.clickDiagnostics();

        Assert.assertTrue(
                diagnostics.isTopCitiesSectionDisplayed(),
                "Top Cities section is not visible on the Diagnostics page"
        );

        System.out.println("TC_12 - Top Cities section is visible on Diagnostics page");
    }
}
