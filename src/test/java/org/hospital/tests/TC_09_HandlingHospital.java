import basetest.BaseTest;
import org.hospital.pages.ParkingFacility;
import org.testng.annotations.Test;

@Test
public class TC_09_HandlingHospital extends BaseTest {
    public void hospitalHandling() {
        ParkingFacility park =new ParkingFacility(driver);
        park.handleHospital();
    }
}