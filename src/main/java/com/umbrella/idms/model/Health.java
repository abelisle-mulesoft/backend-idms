package com.umbrella.idms.model;

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

    private String status = STATUS_UP;
}
