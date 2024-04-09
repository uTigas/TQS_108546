package tqs.hw1.fs_webApp.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableCaching
public class CacheConfig {
    private static final Logger logger = LoggerFactory.getLogger(CacheConfig.class);

    public static final String exRates = "exchangeRates";
    @Bean
    public CacheManager cacheManager() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager("exchangeRates");
        return cacheManager;
    }


    @CacheEvict(allEntries = true, value = {exRates})
    @Scheduled(fixedDelay = 1 * 60 * 1000 ,  initialDelay = 500) //Clear cache after 1 minute
    public void reportCacheEvict() {
        
        logger.info(exRates + " Cache has been flushed");
    }
}
