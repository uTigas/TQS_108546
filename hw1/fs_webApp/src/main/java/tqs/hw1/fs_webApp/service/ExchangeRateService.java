package tqs.hw1.fs_webApp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import tqs.hw1.fs_webApp.support.ExchangeRateApiResponse;

import java.time.Instant;
import java.util.Map;

@Service
public class ExchangeRateService {

    private static final Logger logger = LoggerFactory.getLogger(ExchangeRateService.class);

    @Autowired
    private CacheManager cacheManager;
    private static long ttl = 10; //ttl in seconds
    private final RestTemplate restTemplate = new RestTemplate();
    private final String uri = "https://v6.exchangerate-api.com/v6/8caefa5f8a3011261dc2e868/latest/EUR";

    public Double getExchangeRate(String targetCurrency) {
        Cache exchangeRatesCache = cacheManager.getCache("exchangeRates");
        Cache.ValueWrapper cachedValueWrapper = exchangeRatesCache.get(targetCurrency);

        if (cachedValueWrapper != null ) {
            CachedExchangeRate cachedExchangeRate = (CachedExchangeRate) cachedValueWrapper.get();
            if ((System.currentTimeMillis()/1000 - cachedExchangeRate.getTimestamp()) > ttl){
                logger.info("[RateExchanger] - TTL for Cache Entries has expired -> Deleting Entries", targetCurrency);
            }
            else{
                logger.info("[RateExchanger] - Exchange rate for {} fetched from cache", targetCurrency);
                return cachedExchangeRate.getValue();
            }
        }

        logger.info("[RateExchanger] - Exchange rate for {} not found in cache, fetching from external API", targetCurrency);

        ExchangeRateApiResponse response = restTemplate.getForObject(uri, ExchangeRateApiResponse.class);
        if (response != null && response.getConversionRates() != null) {
            Map<String, Double> conversionRates = response.getConversionRates();
            for (String currency : conversionRates.keySet()) {
                if (exchangeRatesCache.get(currency) == null) {
                    CachedExchangeRate cachedExchangeRate = new CachedExchangeRate(conversionRates.get(currency), Instant.now().getEpochSecond());
                    exchangeRatesCache.put(currency, cachedExchangeRate);
                }
            }
        }

        CachedExchangeRate fetchedExchangeRate = (CachedExchangeRate) exchangeRatesCache.get(targetCurrency).get();
        logger.info("[RateExchanger] - Exchange rate for {} fetched from external API at {}: {}", targetCurrency, fetchedExchangeRate.getTimestamp(), fetchedExchangeRate.getValue());
        return fetchedExchangeRate.getValue();
    }

    private static class CachedExchangeRate {
        private final Double value;
        private final long timestamp;

        public CachedExchangeRate(Double value, long timestamp) {
            this.value = value;
            this.timestamp = timestamp;
        }

        public Double getValue() {
            return value;
        }

        public long getTimestamp() {
            return timestamp;
        }
    }
}
