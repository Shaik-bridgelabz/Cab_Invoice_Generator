public class InvoiceSummary {

    private final int numberOfRides;
    private final double totalFare;
    private final double average;

    public InvoiceSummary(int numberOfRides, double totalFare) {
        this.numberOfRides=numberOfRides;
        this.totalFare=totalFare;
        this.average=this.totalFare/this.numberOfRides;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceSummary that = (InvoiceSummary) o;
        return Double.compare(that.average, average) == 0 &&
                totalFare == that.totalFare &&
                Double.compare(that.numberOfRides, numberOfRides) == 0;
    }
}
