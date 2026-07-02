import basetest.BaseTest;
import org.hospital.pages.ParkingFacility;
import org.testng.annotations.Test;

@Test
public class TC_06_GetLocation extends BaseTest {
    public void getlocation() {
        ParkingFacility park =new ParkingFacility(driver);
        Assert.assertFalse(park.getLocations().isEmpty(), "No hospital locations were found on the page!");
    }
}