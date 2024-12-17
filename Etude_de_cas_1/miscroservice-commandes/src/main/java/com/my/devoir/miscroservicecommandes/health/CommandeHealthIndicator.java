package com.my.devoir.miscroservicecommandes.health;

import com.my.devoir.miscroservicecommandes.repository.CommandeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CommandeHealthIndicator implements HealthIndicator {

    @Autowired
    private CommandeRepo repository;

    @Override
    public Health health() {
        long count = repository.count();
        if (count > 0) {
            return Health.up()
                    .withDetail("commandes-count", count)
                    .build();
        } else {
            return Health.down()
                    .withDetail("reason", "No commandes found")
                    .build();
        }
    }
}
