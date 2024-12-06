package com.brilliantmule.identity.management.service;

public interface HealthService {
    com.brilliantmule.identity.management.model.Health getServiceHealth();

    boolean isServiceHealthy();

    void overrideServiceHealth(com.brilliantmule.identity.management.model.Health health);
}
