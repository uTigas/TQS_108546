package tqs.hw1.fs_webApp.unit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import tqs.hw1.fs_webApp.service.ExchangeRateService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CacheTest {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @Autowired
    private CacheManager cacheManager;

    @MockBean
    private RestTemplate restTemplate;
    

    @Test
    void testGetExchangeRate_CacheHit() {
        Cache exchangeRatesCache = cacheManager.getCache("exchangeRates");
        exchangeRatesCache.put("EUR", 1.0);

        Double rate1 = exchangeRateService.getExchangeRate("USD");
        Double rate2 = exchangeRateService.getExchangeRate("GBP");
        Double rate3 = exchangeRateService.getExchangeRate("BRL");
        double delta = 0.4;


        // Verify that the cache is hit and the value is not retrieved from the REST service
        assertEquals(1.0832, rate1, delta);
        assertEquals(0.8578, rate2, delta);
        assertEquals(5.4829, rate3, delta);

        verify(restTemplate, never()).getForObject(any(String.class), any());
    }
    /*
     
     @Test
     void testGetExchangeRate_CacheTTLExpires() throws InterruptedException {
         Cache exchangeRatesCache = cacheManager.getCache("exchangeRates");
         exchangeRatesCache.put("EUR", 1.0);
 
         exchangeRateService.getExchangeRate("USD");
         
         Thread.sleep(12000l); //Default Cache TTL is 10 seconds, check if it expired after 12 seconds
         
         exchangeRateService.getExchangeRate("GBP");
 
         // Verify that the cache has been refreshed
         verify(restTemplate, atLeast(1)).getForObject(any(String.class), any());
     }
     */
}
