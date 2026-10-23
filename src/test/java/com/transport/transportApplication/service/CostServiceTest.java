package com.transport.transportApplication.service;

import com.transport.transportApplication.model.CostRequest;
import com.transport.transportApplication.model.CostResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CostServiceTest {

    private CostService costService;

    @BeforeEach
    void setUp() {
        // Inicializamos el servicio directamente sin Spring
        costService = new CostService();
    }

    @Test
    @DisplayName("Debe calcular el coste correcto para un servicio ESTÁNDAR")
    void calculateCost_StandardService_Success() {
        // 1. Arrange (Preparar datos)
        CostRequest request = new CostRequest();
        request.setWeightKg(10.0);   // 10kg * 0.75 = 7.5€
        request.setDistanceKm(100.0); // 100km * 0.40 = 40.0€
        request.setServiceType("STANDARD"); // Tarifa base = 15.0€ -> Total esperable = 62.5€

        // 2. Act (Ejecutar el método a probar)
        CostResponse response = costService.calculateCost(request);

        // 3. Assert (Verificar resultados)
        assertNotNull(response);
        assertEquals(15.0, response.getBaseCost());
        assertEquals(7.5, response.getWeightCost());
        assertEquals(40.0, response.getDistanceCost());
        assertEquals(62.5, response.getTotalCost());
        assertEquals("EUR", response.getCurrency());
    }

    @Test
    @DisplayName("Debe aplicar un 35% de recargo cuando el servicio es EXPRESS")
    void calculateCost_ExpressService_AppliesSurcharge() {
        // Arrange
        CostRequest request = new CostRequest();
        request.setWeightKg(10.0);   // 7.5€
        request.setDistanceKm(100.0); // 40.0€
        request.setServiceType("EXPRESS"); // Subtotal: 62.5€ * 1.35 = 84.375 -> Redondeado a 84.38€

        // Act
        CostResponse response = costService.calculateCost(request);

        // Assert
        assertEquals(84.38, response.getTotalCost());
    }
}