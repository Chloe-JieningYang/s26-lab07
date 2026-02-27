package AndrewWebServices;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class AndrewWebServicesTest {
    Database database;
    RecSys recommender;
    PromoService promoService;
    AndrewWebServices andrewWebService;

    @Before
    public void setUp() {
        // Use a fake database for testing
        database = new InMemoryDatabase();
        recommender = new RecSys();
        promoService = new PromoService();

        andrewWebService = new AndrewWebServices(database, recommender, promoService);
    }

    @Test
    public void testLogIn() {
        // This is taking way too long to test
        assertTrue(andrewWebService.logIn("Scotty", 17214));
    }

    @Test
    public void testGetRecommendation() {
        // Use a stub recommender for fast testing
        recommender = new StubRecSys();
        andrewWebService = new AndrewWebServices(database, recommender, promoService);
        assertEquals("Animal House", andrewWebService.getRecommendation("Scotty"));
    }

    @Test
    public void testSendEmail() {
        // Use Mockito to mock PromoService
        PromoService mockPromo = mock(PromoService.class);
        andrewWebService = new AndrewWebServices(database, recommender, mockPromo);
        String testEmail = "test@example.com";
        andrewWebService.sendPromoEmail(testEmail);
        verify(mockPromo, times(1)).mailTo(testEmail);
    }

    @Test
    public void testNoSendEmail() {
        PromoService mockPromo = mock(PromoService.class);
        andrewWebService = new AndrewWebServices(database, recommender, mockPromo);
        // Perform an operation that should NOT trigger email sending, e.g., logIn
        andrewWebService.logIn("Scotty", 17214);
        // Verify that mailTo was never called
        verify(mockPromo, never()).mailTo(anyString());
    }
}
