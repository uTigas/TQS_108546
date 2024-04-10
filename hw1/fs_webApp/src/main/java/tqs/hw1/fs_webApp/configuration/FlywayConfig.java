package tqs.hw1.fs_webApp.configuration;

import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class FlywayConfig {

    @Bean
    FlywayMigrationInitializer flywayInitializer(Flyway flyway) {
        return new FlywayMigrationInitializer(flyway, (f) -> {
        });
    }

    static class Dummy {
    }

    @Bean
    @DependsOn("entityManagerFactory")
    Dummy delayedFlywayInitializer(Flyway flyway, FlywayProperties flywayProperties) {
        if (flywayProperties.isEnabled())
            flyway.migrate();
        return new Dummy();
    }
}