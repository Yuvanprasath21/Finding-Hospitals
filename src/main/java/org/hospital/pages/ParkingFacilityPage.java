package org.hospital.pages;

import basepage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    // TC_06: Open every hospital in a new tab
    public int navigateAndHandleHospital() {
        refresh();
        mainWin = driver.getWindowHandle();
        for (WebElement hn : hospitalName) {
            hn.click();
        }
        allWindows = driver.getWindowHandles();
        return allWindows.size();
    }

    // TC_07: Check "Parking" amenity for each hospital
    public int checkingAminities() {
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
        refresh();
        return count;
    }

    // TC_08: Verify each hospital's "Call" button is clickable
    public boolean callButton() {
        refresh();
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
        refresh();
        phone = new ArrayList<>();
        for (WebElement pn : phoneNumber) {
            if (wait.until(ExpectedConditions.visibilityOf(pn)).isDisplayed()) {
                phone.add(pn.getText().trim());
            }
        }
        return phone;
    }

    // TC_10: Verify doctor count for each hospital
    public boolean checkingDoctor(int totalHospitals) {
        refresh();
        boolean allMatch = true;

        for (int i = 0; i < totalHospitals; i++) {
            refresh();
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

        long lastHeight = 0;
        int stable = 0;
        while (stable < 3) {
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
            long newHeight = ((Number) js.executeScript("return document.body.scrollHeight")).longValue();
            if (newHeight == lastHeight) stable++;
            else { stable = 0; lastHeight = newHeight; }
        }

        List<WebElement> cards = driver.findElements(By.className("listing-doctor-card"));
        System.out.println("Expected: " + expectedCount + " | Actual: " + cards.size());
        return cards.size() == expectedCount;
    }
}