package org.hospital.pages;

import basepage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ParkingFacilityPage extends BasePage {

    private static final String READ_MORE_INFO     = "//span[text()='Read more info']";
    private static final String CALL_BUTTONS       = "//button[normalize-space()='Call Hospital']";
    private static final String PHONE_NUMBER_XPATH = "//div[@data-qa-id='phone_number']";
    private static final String DOCTOR_COUNT_XPATH = "//div[contains(@class,'line-4')]//span[1]//span[3]";
    private static final String HOSPITAL_LINKS     = "//ol//a[@target='_blank']";
    private static final String PARKING_AMENITY    = "//div[starts-with(@class,'pure-g u-spacer--top ')]"
                                                        + "//div[@data-qa-id='amenities_list']"
                                                        + "//span[text()='Parking']";

    private static final int  MAX_CALL_BUTTONS = 10;

    private String mainWindow;

    public ParkingFacilityPage(WebDriver driver) {
        super(driver);
    }

    public int navigateAndHandleHospital() {
        mainWindow = driver.getWindowHandle();
        List<WebElement> hospitalList =
                wait.until(ExpectedConditions
                        .visibilityOfAllElementsLocatedBy(
                                By.xpath(HOSPITAL_LINKS)));
        logger.info("Hospitals found: {}", hospitalList.size());
        logger.info("Opening hospital pages in new tabs");
        for (WebElement hospital : hospitalList) {
            try {
                scrollIntoCenterView(hospital);
                clickByJS(hospital);
            } catch (Exception e) {
                logger.warn(
                        "Failed to click hospital: {}",
                        e.getMessage());
            }
        }
        wait.until(driver -> driver.getWindowHandles().size() > 1);
        Set<String> allWindows = driver.getWindowHandles();
        logger.info("Total tabs opened: {}", allWindows.size() - 1);
        return allWindows.size();
    }

    public int checkingAmenities() {
        int hospitalsWithParking = 0;
        Set<String> allWindows = driver.getWindowHandles();
        for (String win : allWindows) {
            if (win.equals(mainWindow)) {
                continue;
            }
            driver.switchTo().window(win);
            String hospitalTitle = driver.getTitle().split(",")[0];
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(READ_MORE_INFO))).click();
                List<WebElement> parking = driver.findElements(By.xpath(PARKING_AMENITY));
                if (!parking.isEmpty() && parking.get(0).isDisplayed()) {
                    logger.info("{} -> Parking Available", hospitalTitle);
                    hospitalsWithParking++;
                }
                else {
                    logger.info("{} -> Parking NOT available", hospitalTitle);
                }
            }
            catch (Exception e) {
                logger.warn("{} -> Skipped ({})", hospitalTitle, e.getClass().getSimpleName());
            }
            finally {
                driver.close();
            }
        }
        driver.switchTo().window(mainWindow);
        return hospitalsWithParking;
    }

    public boolean callButton() {
        List<WebElement> buttons = driver.findElements(By.xpath(CALL_BUTTONS));
        if (buttons.isEmpty())
        {
            logger.warn("No call buttons found.");
            return false;
        }
        logger.info("Checking clickability of first {} call buttons.", MAX_CALL_BUTTONS);
        int checked = 0;
        for (WebElement btn : buttons) {
            try {
                scrollIntoCenterView(btn);
                wait.until(ExpectedConditions.visibilityOf(btn));
                logger.debug(
                        "Call button {} : displayed={} enabled={}",
                        checked + 1,
                        btn.isDisplayed(),
                        btn.isEnabled());
                checked++;
                if (checked >= MAX_CALL_BUTTONS) {
                    break;
                }
            }
            catch (StaleElementReferenceException e) {
                logger.warn("Call button became stale: {}", e.getMessage());
            }
            catch (Exception e) {
                logger.warn("Call button verification failed: {}", e.getMessage());
            }
        }
        logger.info("Successfully verified {} call button(s).", checked);
        return checked > 0;
    }

    public List<String> getPhoneNumbers() {
        List<String> phones = new ArrayList<>();
        for (int i = 0; i < MAX_CALL_BUTTONS; i++) {
            try {
                List<WebElement> buttons = driver.findElements(By.xpath(CALL_BUTTONS));
                if (i >= buttons.size()) {
                    break;
                }
                WebElement btn = buttons.get(i);
                scrollIntoCenterView(btn);
                clickByJS(btn);
                WebElement phoneElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                                                By.xpath(PHONE_NUMBER_XPATH)));
                String phone = phoneElement.getText().trim();
                if (!phone.isEmpty()) {
                    phones.add(phone);
                    logger.info("Hospital {} : {}", i + 1, phone);
                }
            }
            catch (Exception e) {
                logger.warn("Unable to capture phone number for hospital {} : {}",
                        i + 1,
                            e.getClass().getSimpleName()
                );
            }
        }
        logger.info("Total phone numbers captured: {}", phones.size());
        return phones;
    }

    public boolean checkingDoctor() {
        boolean isDisplayedForAll = true;
        List<WebElement> doctors = driver.findElements(By.xpath(DOCTOR_COUNT_XPATH));
        for (int i = 0; i < doctors.size(); i++) {
            try {
                String doctorCountText = doctors.get(i).getText().trim();
                if (!doctorCountText.isEmpty()) {
                    logger.info(
                            "Hospital {} -> Doctor count displayed: {}",
                            i + 1,
                            doctorCountText);
                }
                else {
                    logger.warn(
                            "Hospital {} -> Doctor count not displayed",
                            i + 1);
                    isDisplayedForAll = false;
                }
            }
            catch (Exception e) {
                logger.warn(
                        "Hospital {} -> Failed: {}",
                        i + 1,
                        e.getMessage());
                isDisplayedForAll = false;
            }
        }
        return isDisplayedForAll;
    }
}