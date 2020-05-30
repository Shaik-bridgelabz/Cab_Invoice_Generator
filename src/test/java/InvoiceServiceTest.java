import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceServiceTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    public RideRepository rideRepository;

    @InjectMocks
    public InvoiceService invoiceServiceNormal;

    @InjectMocks
    public InvoiceService invoiceServicePerimium;

    @Before
    public void setUp() throws Exception {
        invoiceServiceNormal.setCabSubscriptions(CabSubscriptions.NORMAL);
        invoiceServicePerimium.setCabSubscriptions(CabSubscriptions.PREMIUM);
    }

    @Test
    public void givenDistanceandTime_whenCalculatedForNormalSubscription_shouldReturnTotalFare() {
        double result = invoiceServiceNormal.calculateFare(2.0,5);
        assertEquals(25,result,0.0);
    }

    @Test
    public void givenLessDistanceandTime_whenCalculatedForNormalSubscription_shouldReturnTotalFare() {
        double result = invoiceServiceNormal.calculateFare(0.1,1);
        assertEquals(5,result,0.0);
    }

    @Test
    public void givenMultipleRides_whenCalculatedForNormalSubscription_shouldReturnInvoiceSummary() {
        Ride[] rides = { new Ride(2.0, 5),
                         new Ride(0.1, 1)
                        };

        InvoiceSummary summary= invoiceServiceNormal.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary=new InvoiceSummary(2,30);
        assertEquals(expectedInvoiceSummary,summary);
    }

    @Test
    public void givenUserIdAndRides_whenCalculatedForNormalSubscription_should_ReturnInvoiceSummary() {
        String userId="abc@.com";
        Ride[] rides = { new Ride(2.0, 5),
                         new Ride(0.1, 1)
                        };
        when(rideRepository.getRides(ArgumentMatchers.any())).thenReturn(rides);
        InvoiceSummary summary= invoiceServiceNormal.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary=new InvoiceSummary(2,30);
        assertEquals(expectedInvoiceSummary,summary);
    }

    @Test
    public void givenDistanceandTime_whenCalculatedForPremiumSubscription_shouldReturnTotalFare() {
        double result = invoiceServicePerimium.calculateFare(2.0,5);
        assertEquals(40,result,0.0);
    }

    @Test
    public void givenLessDistanceandTime_whenCalculatedForPremiumSubscription_shouldReturnTotalFare() {
        double result = invoiceServicePerimium.calculateFare(0.1,1);
        assertEquals(20,result,0.0);
    }

    @Test
    public void givenMultipleRides_whenCalculatedForPremiumSubscription_shouldReturnInvoiceSummary() {
        Ride[] rides = { new Ride(2.0, 5),
                new Ride(0.1, 1)
        };
        InvoiceSummary summary= invoiceServicePerimium.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary=new InvoiceSummary(2,60);
        assertEquals(expectedInvoiceSummary,summary);
    }

    @Test
    public void givenUserIdAndRides_whenCalculatedForPremiumSubscription_should_ReturnInvoiceSummary() {
        String userId="abc@.com";
        Ride[] rides = { new Ride(2.0, 5),
                new Ride(0.1, 1)
        };
        when(rideRepository.getRides(userId)).thenReturn(rides);
        InvoiceSummary summary= invoiceServicePerimium.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary=new InvoiceSummary(2,60);
        assertEquals(expectedInvoiceSummary,summary);
    }
}