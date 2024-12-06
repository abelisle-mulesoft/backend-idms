package com.brilliantmule.identity.management.service;

import org.springframework.stereotype.Service;

@Service
public class HealthServiceImpl implements HealthService {
    private com.brilliantmule.identity.management.model.Health health = new com.brilliantmule.identity.management.model.Health();

    public com.brilliantmule.identity.management.model.Health getServiceHealth() {
        return health;
    }

    public boolean isServiceHealthy() {
        return health.getStatus().equalsIgnoreCase(com.brilliantmule.identity.management.model.Health.STATUS_UP);
    }

    public void overrideServiceHealth(com.brilliantmule.identity.management.model.Health healthOverride) {
        health.setStatus(healthOverride.getStatus());
    }
}
