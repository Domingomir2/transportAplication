package com.transport.transportApplication.model;

import jakarta.validation.constraints.NotBlank;

public class Shipment {
    @NotBlank(message = "El trackingNumber es obligatorio")
    private String trackingNumber;
    private String origin;
    private String destination;
    private String status; // EJ: "PENDING", "IN_TRANSIT", "DELIVERED"
    private double weightKg;

    // Constructor vacío necesario para la deserialización de Jackson
    public Shipment() {
    }

    // Constructor, Getters y Setters
    public Shipment(String trackingNumber, String origin, String destination, String status, double weightKg) {
        this.trackingNumber = trackingNumber;
        this.origin = origin;
        this.destination = destination;
        this.status = status;
        this.weightKg = weightKg;
    }

    public String getTrackingNumber() { return trackingNumber; }
    public void setTrackingNumber(String trackingNumber) { this.trackingNumber = trackingNumber; }
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public double getWeightKg() { return weightKg; }
    public void setWeightKg(double weightKg) { this.weightKg = weightKg; }
}
