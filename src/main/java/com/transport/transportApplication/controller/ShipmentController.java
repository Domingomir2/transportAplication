package com.transport.transportApplication.controller;

import com.transport.transportApplication.model.Shipment;
import com.transport.transportApplication.repository.ShipmentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shipments")
@CrossOrigin(origins = "*") // Permitir peticiones desde Angular
public class ShipmentController {

    @Autowired
    private ShipmentRepository shipmentRepository;

    // Endpoint para listar todos los envíos
    @GetMapping
    public ResponseEntity<List<Shipment>> getAllShipments() {
        return ResponseEntity.ok(shipmentRepository.findAll());
    }

    // Endpoint para buscar un envío por código de seguimiento
    @GetMapping("/{trackingNumber}")
    public ResponseEntity<Shipment> getShipmentByTracking(@PathVariable String trackingNumber) {
        return shipmentRepository.findByTrackingNumber(trackingNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crea el envío si no existe (201) o lo actualiza si ya existe (200)
    @PostMapping("/add")
    public ResponseEntity<Shipment> createShipment(@Valid @RequestBody Shipment shipment) {
        ShipmentRepository.SaveResult result = shipmentRepository.addShipment(shipment);
        HttpStatus status = result.created() ? HttpStatus.CREATED : HttpStatus.OK;
        return ResponseEntity.status(status).body(result.shipment());
    }
}
