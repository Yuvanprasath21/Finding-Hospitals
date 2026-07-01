package org.hospital.tests;

import java.util.List;

import org.hospital.pages.DiagnosticsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import basetest.BaseTest;

public class TC_13_ExtractTopCities extends BaseTest {

    @Test
    public void verifyAllCityNamesCanBeExtracted() {

        DiagnosticsPage diagnostics = new DiagnosticsPage(driver);

        diagnostics.clickDiagnostics();

        List<String> cities = diagnostics.getTopCities();

        Assert.assertTrue(
                cities.size() > 0,
                "No city names were extracted"
        );

        System.out.println("Extracted Cities: " + cities);
    }
}
