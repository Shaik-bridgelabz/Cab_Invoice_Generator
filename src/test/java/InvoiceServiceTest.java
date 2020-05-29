import org.junit.Assert;
import org.junit.Test;

public class InvoiceServiceTest {

    InvoiceService invoiceServiceNormal = new InvoiceService(CabSubscriptions.NORMAL);
    InvoiceService invoiceServicePerimium = new InvoiceService(CabSubscriptions.PREMIUM);

    @Test
    public void givenDistanceandTime_whenCalculatedForNormalSubscription_shouldReturnTotalFare() {
        double result = invoiceServiceNormal.calculateFare(2.0,5);
        Assert.assertEquals(25,result,0.0);
    }

    @Test
    public void givenLessDistanceandTime_whenCalculatedForNormalSubscription_shouldReturnTotalFare() {
        double result = invoiceServiceNormal.calculateFare(0.1,1);
        Assert.assertEquals(5,result,0.0);
    }

    @Test
    public void givenMultipleRides_whenCalculatedForNormalSubscription_shouldReturnInvoiceSummary() {
        Ride[] rides = { new Ride(2.0, 5),
                         new Ride(0.1, 1)
                        };
        InvoiceSummary summary= invoiceServiceNormal.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary=new InvoiceSummary(2,30);
        Assert.assertEquals(expectedInvoiceSummary,summary);
    }

    @Test
    public void givenUserIdAndRides_whenCalculatedForNormalSubscription_should_ReturnInvoiceSummary() {
        String userId="abc@.com";
        Ride[] rides = { new Ride(2.0, 5),
                         new Ride(0.1, 1)
                        };
        invoiceServiceNormal.addRides(userId,rides);
        InvoiceSummary summary= invoiceServiceNormal.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary=new InvoiceSummary(2,30);
        Assert.assertEquals(expectedInvoiceSummary,summary);
    }

    @Test
    public void givenDistanceandTime_whenCalculatedForPremiumSubscription_shouldReturnTotalFare() {
        double result = invoiceServicePerimium.calculateFare(2.0,5);
        Assert.assertEquals(40,result,0.0);
    }

    @Test
    public void givenLessDistanceandTime_whenCalculatedForPremiumSubscription_shouldReturnTotalFare() {
        double result = invoiceServicePerimium.calculateFare(0.1,1);
        Assert.assertEquals(20,result,0.0);
    }

    @Test
    public void givenMultipleRides_whenCalculatedForPremiumSubscription_shouldReturnInvoiceSummary() {
        Ride[] rides = { new Ride(2.0, 5),
                new Ride(0.1, 1)
        };
        InvoiceSummary summary= invoiceServicePerimium.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary=new InvoiceSummary(2,60);
        Assert.assertEquals(expectedInvoiceSummary,summary);
    }

    @Test
    public void givenUserIdAndRides_whenCalculatedForPremiumSubscription_should_ReturnInvoiceSummary() {
        String userId="abc@.com";
        Ride[] rides = { new Ride(2.0, 5),
                new Ride(0.1, 1)
        };
        invoiceServicePerimium.addRides(userId,rides);
        InvoiceSummary summary= invoiceServicePerimium.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary=new InvoiceSummary(2,60);
        Assert.assertEquals(expectedInvoiceSummary,summary);
    }
}