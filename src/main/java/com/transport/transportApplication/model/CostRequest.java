package com.transport.transportApplication.model;

public class CostRequest {
    private double weightKg;
    private double distanceKm;
    private String serviceType; // "STANDARD" o "EXPRESS"

    // Getters y Setters
    public double getWeightKg() { return weightKg; }
    public void setWeightKg(double weightKg) { this.weightKg = weightKg; }

    public double getDistanceKm() { return distanceKm; }
    public void setDistanceKm(double distanceKm) { this.distanceKm = distanceKm; }

    public String getServiceType() { return serviceType; }
    public void setServiceType(String serviceType) { this.serviceType = serviceType; }
}
