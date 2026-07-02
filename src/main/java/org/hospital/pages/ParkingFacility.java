package org.hospital.pages;

public class ParkingFacility extends CommonCode{

    public ParkingFacility(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//ol//a[@target='_blank']")
    List<WebElement> hospitalName;

    @FindBy(xpath = "//span[contains(@class,'c-locality-info')]//span[1]")
    List<WebElement> locations;

    @FindBy(xpath = "//span[text()='Read more info']")
    WebElement readInfo;

    @FindBy(xpath = "//div[contains(@class,'line-4')]//span[1]//span[1]")
    List<WebElement> doctor;

    public void getLocations(){
        for(WebElement lc : locations){
            driver.switchTo().window(win);
            String hosName = driver.getTitle();
            String[] arr = hosName.split(",");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Read more info']"))).click();
            System.out.println(lc.getText());
        }
    }

    public void getSpecialist(){
        for(WebElement sp : doctor){
            System.out.println(sp.getText());
        }
    }

    public void navigateHospital(){
        mainWin = driver.getWindowHandle();
        Assert.assertTrue(hospitalName.size() > 0, "No hospital links available to click!");
        for(WebElement hn : hospitalName){
            hn.click();
        }
    }

    public void handleHospital() {
        allWindows = driver.getWindowHandles();
        int size = allWindows.size();
        System.out.println(size);
        for (String win : allWindows) {
            if (!win.equals(mainWin)) {
                driver.switchTo().window(win);
                System.out.println(driver.getTitle());
                driver.close();
            }
        }

        driver.switchTo().window(mainWin);
    }
    public void checkingAminities(){
        //Set<String> allWindows = driver.getWindowHandles();
        int count = 0;
        for (String win : allWindows) {
            if (!win.equals(mainWin)) {
                driver.switchTo().window(win);
                String hosName = driver.getTitle();
                String[] arr = hosName.split(",");
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Read more info']"))).click();
                boolean check = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[starts-with(@class,'pure-g u-spacer--top ')]//div[@data-qa-id='amenities_list']//span[text()='Parking']"))).isDisplayed();
                if(check){
                    System.out.print(arr[0]+" -> ");
                    count++;
                }
                System.out.println();
                driver.close();
            }
        }
        Assert.assertTrue(count > 0, "None of the checked hospitals had the Parking amenity!");
        System.out.println("Total hospitals with parking: " + count);
        System.out.println(count);
    }
}
