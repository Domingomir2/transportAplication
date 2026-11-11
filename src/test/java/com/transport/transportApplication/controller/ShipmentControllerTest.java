package com.transport.transportApplication.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ShipmentController.class)
class ShipmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ShipmentRepository shipmentRepository;

    @Test
    void getShipmentByTracking_WhenFound_Returns200() throws Exception {
        // Arrange
        Shipment mockShipment = new Shipment("TRK-001", "Madrid", "Barcelona", "IN_TRANSIT", 45.5);
        when(shipmentRepository.findByTrackingNumber("TRK-001")).thenReturn(Optional.of(mockShipment));

        // Act & Assert
        mockMvc.perform(get("/api/v1/shipments/TRK-001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trackingNumber").value("TRK-001"))
                .andExpect(jsonPath("$.origin").value("Madrid"))
                .andExpect(jsonPath("$.status").value("IN_TRANSIT"));
    }

    @Test
    void getShipmentByTracking_WhenNotFound_Returns404() throws Exception {
        // Arrange
        when(shipmentRepository.findByTrackingNumber("NOT-EXISTS")).thenReturn(Optional.empty());

        // Act & Assert
        mockMvc.perform(get("/api/v1/shipments/NOT-EXISTS"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createShipment_WhenNew_Returns201() throws Exception {
        // Arrange
        Shipment shipment = new Shipment("TRK-004", "Madrid", "Sevilla", "PENDING", 10.0);
        when(shipmentRepository.addShipment(any(Shipment.class)))
                .thenReturn(new ShipmentRepository.SaveResult(shipment, true));

        // Act & Assert
        mockMvc.perform(post("/api/v1/shipments/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "trackingNumber": "TRK-004",
                                  "origin": "Madrid",
                                  "destination": "Sevilla",
                                  "status": "PENDING",
                                  "weightKg": 10.0
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.trackingNumber").value("TRK-004"));
    }

    @Test
    void createShipment_WhenExists_Returns200() throws Exception {
        // Arrange
        Shipment shipment = new Shipment("TRK-001", "Lisboa", "Oporto", "DELIVERED", 80.0);
        when(shipmentRepository.addShipment(any(Shipment.class)))
                .thenReturn(new ShipmentRepository.SaveResult(shipment, false));

        // Act & Assert
        mockMvc.perform(post("/api/v1/shipments/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "trackingNumber": "TRK-001",
                                  "origin": "Lisboa",
                                  "destination": "Oporto",
                                  "status": "DELIVERED",
                                  "weightKg": 80.0
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.origin").value("Lisboa"));
    }

    @Test
    void createShipment_WithBlankTrackingNumber_Returns400() throws Exception {
        // Act & Assert
        mockMvc.perform(post("/api/v1/shipments/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "trackingNumber": "   ",
                                  "origin": "Madrid",
                                  "destination": "Sevilla",
                                  "status": "PENDING",
                                  "weightKg": 10.0
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.trackingNumber").value("El trackingNumber es obligatorio"));
    }
}
