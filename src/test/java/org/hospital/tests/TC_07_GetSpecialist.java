import basetest.BaseTest;
import org.hospital.pages.ParkingFacility;
import org.testng.annotations.Test;

@Test
public class TC_06_GetSpecialist extends BaseTest {
    public void specialist() {
        ParkingFacility park =new ParkingFacility(driver);
        Assert.assertFalse(park.getSpecialist().isEmpty(), "No specialists were found on the page!");
    }
}