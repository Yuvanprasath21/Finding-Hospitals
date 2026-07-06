package org.hospital.tests;

import java.io.IOException;
import java.util.List;
import org.hospital.pages.DiagnosticsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ExcelUtils;
import basetest.BaseTest;

public class TC_12_TopAllCitiesPage extends BaseTest {

    @Test
    public void verifyCitiesExtractedAndStoredInExcel() throws IOException {
        driver.get(properties.getProperty("website_url"));
        DiagnosticsPage diagnostics = new DiagnosticsPage(driver);
        diagnostics.clickDiagnostics();
        List<String> topCities = diagnostics.getTopCities();
        List<String> allCities = diagnostics.getAllCities();
        Assert.assertFalse(
                topCities.isEmpty(),
                "Top Cities could not be extracted"
        );

        Assert.assertFalse(
                allCities.isEmpty(),
                "All Cities could not be extracted"
        );

        // Print extracted cities
        System.out.println("\nTOP CITIES:");
        System.out.println(String.join(", ", topCities));

        System.out.println("ALL CITIES:");
        System.out.println(String.join(", ", allCities));

        ExcelUtils.writeCitiesToExcel(topCities, allCities);
        System.out.println(
                "TC_12 - Top Cities and All Cities extracted and stored successfully in Excel\n"
        );
    }
}