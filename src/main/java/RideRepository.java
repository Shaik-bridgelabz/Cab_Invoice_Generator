import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RideRepository {

    Map<String, ArrayList<Ride>> userRides=null;

    public RideRepository() {
        this.userRides=new HashMap<>();
    }

    public void addRides(String userId, Ride[] rides) {
        try {
            if (userId.length() == 0)
                throw new CabInvoiceException("UserId cannot be Empty", CabInvoiceException.ExceptionType.ENTERED_EMPTY);
            ArrayList<Ride> rideList = this.userRides.get(userId);
            if (rideList == null) {
                this.userRides.put(userId, new ArrayList<>(Arrays.asList(rides)));
            }
        } catch (NullPointerException e) {
            throw new CabInvoiceException("UserId cannot be Null", CabInvoiceException.ExceptionType.ENTERED_NULL);
        }
    }

    public Ride[] getRides(String userId) {
        return this.userRides.get(userId).toArray(new Ride[0]);
    }
}
