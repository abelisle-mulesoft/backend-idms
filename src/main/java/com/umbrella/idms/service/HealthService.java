package com.umbrella.idms.service;

import com.umbrella.idms.model.Health;

public interface HealthService {
    Health getServiceHealth();

    boolean isServiceHealthy();

    void overrideServiceHealth(Health health);
}
