package com.umbrella.idms.service;

import com.umbrella.idms.model.Health;
import org.springframework.stereotype.Service;

@Service
public class HealthServiceImpl implements HealthService {
    private Health health = new Health();

    public Health getServiceHealth() {
        return health;
    }

    public boolean isServiceHealthy() {
        return health.getStatus().equalsIgnoreCase(Health.STATUS_UP);
    }

    public void overrideServiceHealth(Health healthOverride) {
        health.setStatus(healthOverride.getStatus());
    }
}
