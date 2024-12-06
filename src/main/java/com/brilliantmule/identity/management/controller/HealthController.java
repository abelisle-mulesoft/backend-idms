package com.brilliantmule.identity.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class HealthController {
    @Autowired
    com.brilliantmule.identity.management.service.HealthService healthService;
    @Autowired
    private ApplicationContext context;

    @GetMapping("/health")
    private com.brilliantmule.identity.management.model.Health getServiceHealth() {
        return healthService.getServiceHealth();
    }

    @PutMapping("/health")
    private void overrideServiceHealth(@RequestBody com.brilliantmule.identity.management.model.Health health) {
        if (health.getStatus().equalsIgnoreCase(com.brilliantmule.identity.management.model.Health.STATUS_SHUTDOWN) || health.getStatus().equalsIgnoreCase(com.brilliantmule.identity.management.model.Health.STATUS_STOP)) {
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
