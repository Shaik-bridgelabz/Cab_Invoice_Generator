import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InvoiceServiceTest {

    InvoiceService invoiceService =null;

    @Before
    public void setUp() throws Exception {
        invoiceService =new InvoiceService();
    }

    @Test
    public void givenDistanceandTime_whenCalculated_shouldReturnTotalFare() {
        double result = invoiceService.calculateFare(2.0,5);
        Assert.assertEquals(25,result,0.0);
    }

    @Test
    public void givenLessDistanceandTime_whenCalculated_shouldReturnTotalFare() {
        double result = invoiceService.calculateFare(0.1,1);
        Assert.assertEquals(5,result,0.0);
    }

    @Test
    public void givenMultipleRides_whenCalculated_shouldReturnInvoiceSummary() {
        Ride[] rides = { new Ride(2.0, 5),
                         new Ride(0.1, 1)
                        };
        InvoiceSummary summary= invoiceService.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary=new InvoiceSummary(2,30);
        Assert.assertEquals(expectedInvoiceSummary,summary);
    }

    @Test
    public void givenUserIdAndRides_whenCalculated_should_ReturnInvoiceSummary() {
        String userId="abc@.com";
        Ride[] rides = { new Ride(2.0, 5),
                         new Ride(0.1, 1)
                        };
        invoiceService.addRides(userId,rides);
        InvoiceSummary summary=invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary=new InvoiceSummary(2,30);
        Assert.assertEquals(expectedInvoiceSummary,summary);
    }
}