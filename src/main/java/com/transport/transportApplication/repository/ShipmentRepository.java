package com.transport.transportApplication.repository;

import com.transport.transportApplication.model.Shipment;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ShipmentRepository {

    // Datos simulados (Mock data).
    // La clave del mapa es el trackingNumber normalizado: sin espacios y en mayúsculas.
    private final Map<String, Shipment> shipments = new ConcurrentHashMap<>();
    private final ObservationRegistry observationRegistry;

    public ShipmentRepository() {
        this(ObservationRegistry.NOOP);
    }

    @Autowired
    public ShipmentRepository(ObservationRegistry observationRegistry) {
        this.observationRegistry = observationRegistry;
        addInitialShipment(new Shipment("TRK-001", "Madrid", "Barcelona", "IN_TRANSIT", 45.5));
        addInitialShipment(new Shipment("TRK-002", "Valencia", "Sevilla", "PENDING", 120.0));
        addInitialShipment(new Shipment("TRK-003", "Bilbao", "Zaragoza", "DELIVERED", 15.2));
    }

    /**
     * Devuelve una copia inmutable ordenada por trackingNumber, para que nadie
     * pueda modificar el estado interno del repositorio desde fuera.
     */
    public List<Shipment> findAll() {
        return Observation.createNotStarted("transport.shipment.find_all", observationRegistry)
                .observe(() -> shipments.values().stream()
                        .sorted(Comparator.comparing(Shipment::getTrackingNumber))
                        .toList());
    }

    public Optional<Shipment> findByTrackingNumber(String trackingNumber) {
        return Observation.createNotStarted("transport.shipment.find_by_tracking_number", observationRegistry)
                .observe(() -> {
                    String key = normalizeTrackingNumber(trackingNumber);
                    if (key == null) {
                        return Optional.empty();
                    }
                    return Optional.ofNullable(shipments.get(key));
                });
    }

    /**
     * Si ya existe un envío con el mismo trackingNumber, reemplaza el objeto completo.
     * Si no existe, lo inserta.
     *
     * @return el envío guardado y si la operación ha sido una creación
     */
    public SaveResult addShipment(Shipment shipment) {
        return Observation.createNotStarted("transport.shipment.save", observationRegistry)
                .observe(() -> save(shipment));
    }

    private SaveResult save(Shipment shipment) {
        if (shipment == null) {
            throw new IllegalArgumentException("El envío no puede ser nulo");
        }
        String key = normalizeTrackingNumber(shipment.getTrackingNumber());
        if (key == null) {
            throw new IllegalArgumentException("El trackingNumber del envío es obligatorio");
        }

        // Guardamos siempre el trackingNumber normalizado para que las búsquedas sean consistentes.
        shipment.setTrackingNumber(key);

        Shipment previous = shipments.put(key, shipment);
        return new SaveResult(shipment, previous == null);
    }

    private void addInitialShipment(Shipment shipment) {
        shipments.put(shipment.getTrackingNumber(), shipment);
    }

    private String normalizeTrackingNumber(String trackingNumber) {
        if (trackingNumber == null) {
            return null;
        }
        String normalized = trackingNumber.trim().toUpperCase(Locale.ROOT);
        return normalized.isEmpty() ? null : normalized;
    }

    /**
     * Resultado de guardar un envío: el objeto guardado y si era nuevo (created)
     * o una actualización de uno existente (created = false).
     */
    public record SaveResult(Shipment shipment, boolean created) {
    }
}
