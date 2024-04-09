package tqs.hw1.fs_webApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import tqs.hw1.fs_webApp.support.ExchangeRateApiResponse;

import java.util.Map;

@Service
public class ExchangeRateService {

    @Autowired
    private CacheManager cacheManager;

    private final RestTemplate restTemplate = new RestTemplate();
    private final String uri = "https://v6.exchangerate-api.com/v6/8caefa5f8a3011261dc2e868/latest/EUR";

    @Cacheable(value = "exchangeRates", key = "#targetCurrency", unless = "#result == null")
    public Double getExchangeRate(String targetCurrency) {
        Cache exchangeRatesCache = cacheManager.getCache("exchangeRates");
        Double cachedValue = exchangeRatesCache.get(targetCurrency, Double.class);

        if (cachedValue != null) {
            return cachedValue;
        }

        ExchangeRateApiResponse response = restTemplate.getForObject(uri, ExchangeRateApiResponse.class);
        if (response != null && response.getConversionRates() != null) {
            Map<String, Double> conversionRates = response.getConversionRates();
            for (String currency : conversionRates.keySet()) {
                if (exchangeRatesCache.get(currency) == null) {
                    exchangeRatesCache.put(currency, conversionRates.get(currency));
                }
            }
        }
        
        return (Double) exchangeRatesCache.get(targetCurrency, Double.class);
    }
}
