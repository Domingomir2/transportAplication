package com.transport.transportApplication.model;

public class CostResponse {
    private final double baseCost;
    private final double weightCost;
    private final double distanceCost;
    private final double totalCost;
    private static String currency = "EUR";

    public CostResponse(double baseCost, double weightCost, double distanceCost, double totalCost) {
        this.baseCost = baseCost;
        this.weightCost = weightCost;
        this.distanceCost = distanceCost;
        this.totalCost = totalCost;
    }

    // Getters
    public double getBaseCost() { return baseCost; }
    public double getWeightCost() { return weightCost; }
    public double getDistanceCost() { return distanceCost; }
    public double getTotalCost() { return totalCost; }
    public String getCurrency() { return currency; }
}
