import basetest.BaseTest;
import org.hospital.pages.ParkingFacility;
import org.testng.annotations.Test;

@Test
public class TC_08_NavigatingHospital extends BaseTest {
    public void hospitalNavigation() {
        ParkingFacility park =new ParkingFacility(driver);
        park.navigateHospital();
    }
}