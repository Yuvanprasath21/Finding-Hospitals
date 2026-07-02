import basetest.BaseTest;
import org.hospital.pages.ParkingFacility;
import org.testng.annotations.Test;

@Test
public class TC_06_GetLocation extends BaseTest {
    public void getlocation() {
        ParkingFacility park =new ParkingFacility(driver);
        park.getLocations()
    }
}