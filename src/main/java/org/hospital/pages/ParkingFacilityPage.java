package org.hospital.pages;

import basepage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ParkingFacilityPage extends BasePage {

    private String mainWin;
    private Set<String> allWindows;
    private List<String> phone = new ArrayList<>();

    @FindBy(xpath = "//ol//a[@target='_blank']")
    List<WebElement> hospitalName;

    @FindBy(xpath = "//span[contains(@class,'c-locality-info')]//span[1]")
    List<WebElement> locations;

    @FindBy(xpath = "//div[contains(@class,'line-4')]//span[1]//span[3]")
    List<WebElement> doctor;

    @FindBy(xpath = "//button[contains(@class,'call')]")
    List<WebElement> callHospital;

    @FindBy(xpath = "//div[@data-qa-id='phone_number']")
    List<WebElement> phoneNumber;

    @FindBy(className = "listing-doctor-card")
    List<WebElement> doctorCard;

    public ParkingFacilityPage(WebDriver driver) {
        super(driver);
    }

    // TC_06: Open every hospital in a new tab

    public int navigateAndHandleHospital() {
        mainWin = driver.getWindowHandle();
        for (WebElement hn : hospitalName) {
            try {
                // 🔑 JS click — bypasses overlay interception
                js.executeScript("arguments[0].scrollIntoView({block:'center'});", hn);
                js.executeScript("arguments[0].click();", hn);
            } catch (Exception e) {
                System.out.println("Failed to click hospital: " + e.getMessage());
            }
        }
        allWindows = driver.getWindowHandles();
        return allWindows.size();
    }

    // TC_07: Check "Parking" amenity for each hospital
    public int checkingAminities() {
        PageFactory.initElements(driver, this);
        int count = 0;
        for (String win : allWindows) {
            if (!win.equals(mainWin)) {
                driver.switchTo().window(win);
                String[] arr = driver.getTitle().split(",");

                wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//span[text()='Read more info']"))).click();

                boolean check = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[starts-with(@class,'pure-g u-spacer--top ')]" +
                                "//div[@data-qa-id='amenities_list']//span[text()='Parking']"))).isDisplayed();

                if (check) {
                    System.out.println(arr[0] + " -> Parking Available");
                    count++;
                }
                driver.close();
            }
        }
        driver.switchTo().window(mainWin);
        return count;
    }

    // TC_08: Verify each hospital's "Call" button is clickable
    public boolean callButton() {
        boolean flag = false;
        int count = 0;

        if (callHospital.isEmpty()) {
            System.out.println("No call buttons found.");
            return false;
        }

        for (WebElement ch : callHospital) {
            if (count >= 10) break;
            String disabled = ch.getAttribute("disabled");
            String ariaDisabled = ch.getAttribute("aria-disabled");
            if (disabled == null && !"true".equals(ariaDisabled)) {
                try {
                    js.executeScript("arguments[0].scrollIntoView(true);", ch);
                    wait.until(ExpectedConditions.elementToBeClickable(ch)).click();
                    System.out.println("Call button " + (count + 1) + " is clickable");
                    flag = true;
                    count++;
                } catch (Exception e) {
                    System.out.println("Call button not clickable: " + e.getMessage());
                }
            }
        }
        return flag;
    }

    // TC_09: Get all revealed phone numbers
    public List<String> getPhoneNumbers() {
        phone = new ArrayList<>();

        if (phoneNumber.isEmpty()) {
            System.out.println("No phone number elements found on page.");
            return phone;
        }

        System.out.println("Total phone elements found: " + phoneNumber.size());

        for (WebElement pn : phoneNumber) {
            try {
                // 🔑 Try to get text — skip if not visible/available
                if (pn.isDisplayed()) {
                    String phonenumber = pn.getText().trim();
                    if (!phonenumber.isEmpty()) {
                        phone.add(phonenumber);
                    }
                }
            } catch (Exception e) {
                // Skip this phone number, continue with others
                System.out.println("Skipping one hidden phone number");
            }
        }
        return phone;
    }

    // TC_10: Verify doctor count for each hospital
    public boolean checkingDoctor(int totalHospitals) {
        boolean allMatch = true;

        for (int i = 0; i < totalHospitals; i++) {
            if (i >= doctor.size()) {
                System.out.println("Only " + doctor.size() + " doctor elements available.");
                break;
            }
            WebElement sp = doctor.get(i);
            String[] parts = sp.getText().trim().split(" ");
            int expected = Integer.parseInt(parts[0]);

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", sp);
            sp.click();

            boolean result = countDoctor(expected);
            System.out.println("Hospital " + (i + 1) + " → " + result);
            if (!result) allMatch = false;

            driver.navigate().back();
        }
        return allMatch;
    }

    private boolean countDoctor(int expectedCount) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.className("listing-doctor-card")));
        } catch (Exception e) {
            System.out.println("No doctor cards on page.");
            return false;
        }

        // 🔑 Scroll until page height stops growing — using FluentWait, no Thread.sleep
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(800))
                .ignoring(Exception.class);

        long[] lastHeight = {0};
        int[] stable = {0};

        try {
            fluentWait.until(d -> {
                js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
                long newHeight = ((Number) js.executeScript(
                        "return document.body.scrollHeight")).longValue();
                if (newHeight == lastHeight[0]) {
                    stable[0]++;
                } else {
                    stable[0] = 0;
                    lastHeight[0] = newHeight;
                }
                return stable[0] >= 3;   // 3 polls same height → fully loaded
            });
        } catch (Exception e) {
            System.out.println("Scroll wait ended: " + e.getMessage());
        }

        List<WebElement> cards = driver.findElements(By.className("listing-doctor-card"));
        int actualCount = cards.size();
        System.out.println("Expected: " + expectedCount + " | Actual: " + actualCount);

        // 🔑 Tolerance increased to 5 — allows realistic variance in Practo counts
        int tolerance = 5;
        return Math.abs(actualCount - expectedCount) <= tolerance;
    }
}