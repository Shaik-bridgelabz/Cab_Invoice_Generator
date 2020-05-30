public class InvoiceService {

    public static final double MINIMUM_COST_PER_KILOMETER_NORMAL = 10 ;
    public static final int COST_PER_TIME_NORMAL = 1 ;
    public static final int MINIMUM_FARE_NORMAL=5;

    public static final double MINIMUM_COST_PER_KILOMETER_PREMIUM = 15 ;
    public static final int COST_PER_TIME_PREMIUM = 2 ;
    public static final int MINIMUM_FARE_PREMIUM=20;
    double totalFare=0;

    private CabSubscriptions cabSubscriptions;
    public RideRepository rideRepository;

    public InvoiceService(CabSubscriptions cabSubscriptions) {
        this.rideRepository=new RideRepository();
        this.cabSubscriptions=cabSubscriptions;
    }

    public void setCabSubscriptions(CabSubscriptions cabSubscriptions) {
        this.cabSubscriptions = cabSubscriptions;
    }

    public double calculateFare(double distance, int time) {
        if (CabSubscriptions.NORMAL.equals(this.cabSubscriptions)) {
            double totalFare = distance * MINIMUM_COST_PER_KILOMETER_NORMAL + time * COST_PER_TIME_NORMAL;
            return Math.max(totalFare, MINIMUM_FARE_NORMAL);
        }
        if (CabSubscriptions.PREMIUM.equals(this.cabSubscriptions)) {
            double totalFare = distance * MINIMUM_COST_PER_KILOMETER_PREMIUM + time * COST_PER_TIME_PREMIUM;
            return Math.max(totalFare, MINIMUM_FARE_PREMIUM);
        }
        throw new CabInvoiceException("Subscription Type is Not Proper", CabInvoiceException.ExceptionType.IMPROPER_SUBSCRIPTION);
    }

    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare=0;
        for (Ride ride : rides) {
            totalFare+=this.calculateFare(ride.distance,ride.time);
        }
        return new InvoiceSummary(rides.length, (int)totalFare);
    }

    public void addRides(String userId, Ride[] rides) {
        rideRepository.addRides(userId,rides);
    }

    public InvoiceSummary getInvoiceSummary(String userId) {
        return this.calculateFare(rideRepository.getRides(userId));
    }
}
