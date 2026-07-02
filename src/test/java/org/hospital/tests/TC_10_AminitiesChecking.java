import basetest.BaseTest;
import org.hospital.pages.ParkingFacility;
import org.testng.annotations.Test;

@Test
public class TC_10_AminitiesChecking extends BaseTest {
    public void hospitalHandling() {
        ParkingFacility park =new ParkingFacility(driver);
        park.checkingAminities();
    }
}