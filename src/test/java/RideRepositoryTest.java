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

    @Test
    public void givenUserId_whenNotFound_shouldThrowException() {
        RideRepository rideRepository=new RideRepository();
        Ride[] rides = { new Ride(2.0, 5),
                new Ride(0.1, 1)
        };
        try {
        rideRepository.addRides("",rides);
        } catch (CabInvoiceException e) {
            Assert.assertEquals(CabInvoiceException.ExceptionType.ENTERED_EMPTY,e.type);
        }
    }

    @Test
    public void givenUserId_whenNull_shouldThrowException() {
        RideRepository rideRepository=new RideRepository();
        Ride[] rides = { new Ride(2.0, 5),
                new Ride(0.1, 1)
        };
        try {
            rideRepository.addRides(null,rides);
        } catch (CabInvoiceException e) {
            Assert.assertEquals(CabInvoiceException.ExceptionType.ENTERED_NULL,e.type);
        }
    }
}