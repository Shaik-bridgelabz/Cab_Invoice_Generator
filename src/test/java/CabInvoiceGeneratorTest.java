import org.junit.Assert;
import org.junit.Test;

public class CabInvoiceGeneratorTest {

    @Test
    public void givenDistanceandTime_whenCalculated_shoulsReturnTotalFare() {
        CabInvoiceGenerator cabInvoiceGenerator=new CabInvoiceGenerator();
        double result = cabInvoiceGenerator.calculateFare(2.0,5);
        Assert.assertEquals(25,result,0.0);
    }

    @Test
    public void givenLessDistanceandTime_whenCalculated_shoulsReturnTotalFare() {
        CabInvoiceGenerator cabInvoiceGenerator=new CabInvoiceGenerator();
        double result = cabInvoiceGenerator.calculateFare(0.1,1);
        Assert.assertEquals(5,result,0.0);
    }

    @Test
    public void givenMultipleRides_whenCalculated_shouldReturnInvoiceSummary() {
        CabInvoiceGenerator cabInvoiceGenerator=new CabInvoiceGenerator();
        Ride[] rides = { new Ride(2.0, 5),
                new Ride(0.1, 1)
        };
        InvoiceSummary summary=cabInvoiceGenerator.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary=new InvoiceSummary(2,30);
        Assert.assertEquals(expectedInvoiceSummary,summary);
    }
}