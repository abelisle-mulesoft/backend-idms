package com.brilliantmule.identity.management.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@Tag(name = "Health")
public class HealthController {
    @Autowired
    com.brilliantmule.identity.management.service.HealthService healthService;
    @Autowired
    private ApplicationContext context;

    @GetMapping("/health")
    @Operation(
            operationId = "get-service-health",
            summary = "Get health status",
            description = "Get the health and status of this microservice",
            responses = { @ApiResponse(
                    responseCode = "200",
                    content = { @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = com.brilliantmule.identity.management.model.Health.class)
                    ) }
            ) }
    )
    private com.brilliantmule.identity.management.model.Health getServiceHealth() {
        return healthService.getServiceHealth();
    }

    @Hidden
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
