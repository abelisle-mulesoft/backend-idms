package com.umbrella.idms.controller;

import com.umbrella.idms.model.Health;
import com.umbrella.idms.service.HealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/idms")
public class HealthController {
    @Autowired
    HealthService healthService;
    @Autowired
    private ApplicationContext context;

    @GetMapping("/health")
    private Health getServiceHealth() {
        return healthService.getServiceHealth();
    }

    @PutMapping("/health")
    private void overrideServiceHealth(@RequestBody Health health) {
        if (health.getStatus().equalsIgnoreCase(Health.STATUS_SHUTDOWN) || health.getStatus().equalsIgnoreCase(Health.STATUS_STOP)) {
            initiateAppShutdown(0);
        } else {
            healthService.overrideServiceHealth(health);
        }
    }

    private void initiateAppShutdown(int returnCode) {
        System.out.println("Shutting down application (return code: " + returnCode + ")");
        SpringApplication.exit(context, () -> returnCode);
    }
}
