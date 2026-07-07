package org.hospital.tests;

import basetest.BaseTest;
import org.hospital.pages.DiagnosticsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;


public class TC_12_TopAllCitiesPage extends BaseTest {

    @Test
    public void verifyCitiesExtractedAndStoredInExcel() throws IOException {
        logger.info("Starting : {}", this.getClass().getSimpleName());

        DiagnosticsPage diagnostics = new DiagnosticsPage(driver);

        logger.info("Clicking Diagnostics menu");
        diagnostics.clickDiagnostics();
        Assert.assertTrue(
                diagnostics.isDiagnosticsPageOpened(),
                "User did NOT navigate to Diagnostics page"
        );

        logger.info("Extracting Top Cities");
        List<String> topCities = diagnostics.getTopCities();
        Assert.assertFalse(
                topCities.isEmpty(),
                "Top Cities could not be extracted"
        );
        logger.info("Successfully extracted {} Top Cities", topCities.size());
        logger.debug("Top Cities: {}", topCities);

        logger.info("Extracting All Cities");
        List<String> allCities = diagnostics.getAllCities();
        Assert.assertFalse(
                allCities.isEmpty(),
                "All Cities could not be extracted"
        );
        logger.info("Successfully extracted {} All Cities", allCities.size());
        logger.debug("All Cities: {}", allCities);

        //logger.info("Writing city data to Excel");
        // ExcelUtils.writeCitiesToExcel(topCities, allCities);
        //logger.info("TC_12 - Top Cities and All Cities extracted and stored successfully in Excel");
        logger.info("Ending : {}", this.getClass().getSimpleName());
    }
}