import org.junit.Assert;
import org.junit.Test;

public class RideRepositoryTest {

    @Test
    public void givenUserId_whenFound_shouldReturnRideList() {
        RideRepository rideRepository=new RideRepository();
        Ride[] rides = { new Ride(2.0, 5),
                new Ride(0.1, 1)
        };
        rideRepository.addRides("abc@gmail.com",rides);
        Assert.assertEquals(rideRepository.getRides("abc@gmail.com").length,rides.length);
    }
}