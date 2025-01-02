package com.brilliantmule.identity.management.model;

import io.swagger.v3.oas.annotations.media.Schema;

public class Health {
    public static final String STATUS_UP = "UP";
    public static final String STATUS_DOWN = "DOWN";
    public static final String STATUS_SHUTDOWN = "SHUTDOWN";
    public static final String STATUS_STOP = "STOP";
    public static final String STATUS_UNKNOWN = "UNKNOWN";

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Schema(
            description = "The health status of this microservice",
            example = "UP"
    )
    private String status = STATUS_UP;
}
