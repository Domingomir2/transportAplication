package com.transport.transportApplication.service;

import com.transport.transportApplication.model.CostRequest;
import com.transport.transportApplication.model.CostResponse;
import org.springframework.stereotype.Service;

@Service
public class CostService {

    public CostResponse calculateCost(CostRequest request) {
        double baseCost = 15.0; // Tarifa fija de gestión
        double weightCost = request.getWeightKg() * 0.75; // 0.75€ por kg
        double distanceCost = request.getDistanceKm() * 0.40; // 0.40€ por km

        double subtotal = baseCost + weightCost + distanceCost;

        // Si es servicio express, aplicamos un 35% de recargo
        if ("EXPRESS".equalsIgnoreCase(request.getServiceType())) {
            subtotal *= 1.35;
        }

        // Redondeamos a 2 decimales para que quede prolijo
        double totalCost = Math.round(subtotal * 100.0) / 100.0;

        return new CostResponse(baseCost, weightCost, distanceCost, totalCost);
    }
}