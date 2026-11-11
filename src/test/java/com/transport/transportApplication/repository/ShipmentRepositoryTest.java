package com.transport.transportApplication.repository;

import com.transport.transportApplication.model.Shipment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShipmentRepositoryTest {

    private ShipmentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new ShipmentRepository();
    }

    @Test
    void addShipment_WhenNotExists_AddsItAndReturnsCreated() {
        Shipment newShipment = new Shipment("TRK-004", "Madrid", "Sevilla", "PENDING", 10.0);

        ShipmentRepository.SaveResult result = repository.addShipment(newShipment);

        assertTrue(result.created());
        assertSame(newShipment, result.shipment());
        assertEquals(4, repository.findAll().size());
        assertEquals(newShipment, repository.findByTrackingNumber("TRK-004").orElseThrow());
    }

    @Test
    void addShipment_WhenExists_ReplacesWholeObjectAndReturnsNotCreated() {
        Shipment updated = new Shipment("TRK-001", "Lisboa", "Oporto", "DELIVERED", 80.0);

        ShipmentRepository.SaveResult result = repository.addShipment(updated);

        assertFalse(result.created());
        assertEquals(3, repository.findAll().size());

        Shipment stored = repository.findByTrackingNumber("TRK-001").orElseThrow();
        assertSame(updated, stored);
        assertEquals("Lisboa", stored.getOrigin());
        assertEquals("Oporto", stored.getDestination());
        assertEquals("DELIVERED", stored.getStatus());
        assertEquals(80.0, stored.getWeightKg());
    }

    @Test
    void addShipment_NormalizesTrackingNumber() {
        ShipmentRepository.SaveResult result =
                repository.addShipment(new Shipment("  trk-004  ", "Madrid", "Sevilla", "PENDING", 10.0));

        assertTrue(result.created());
        assertEquals("TRK-004", result.shipment().getTrackingNumber());
        assertTrue(repository.findByTrackingNumber("trk-004").isPresent());
    }

    @Test
    void addShipment_UpdatesRegardlessOfCaseAndWhitespace() {
        Shipment updated = new Shipment("  trk-002  ", "Lisboa", "Oporto", "DELIVERED", 80.0);

        ShipmentRepository.SaveResult result = repository.addShipment(updated);

        assertFalse(result.created());
        assertEquals(3, repository.findAll().size());
        assertEquals("Lisboa", repository.findByTrackingNumber("TRK-002").orElseThrow().getOrigin());
    }

    @Test
    void addShipment_WhenNull_ThrowsIllegalArgument() {
        assertThrows(IllegalArgumentException.class, () -> repository.addShipment(null));
    }

    @Test
    void addShipment_WhenTrackingNumberIsBlank_ThrowsIllegalArgument() {
        Shipment blank = new Shipment("   ", "Madrid", "Sevilla", "PENDING", 10.0);

        assertThrows(IllegalArgumentException.class, () -> repository.addShipment(blank));
    }

    @Test
    void findByTrackingNumber_WhenNull_ReturnsEmpty() {
        assertTrue(repository.findByTrackingNumber(null).isEmpty());
    }

    @Test
    void findAll_ReturnsUnmodifiableList() {
        List<Shipment> shipments = repository.findAll();
        Shipment newShipment = new Shipment("TRK-999", "A", "B", "PENDING", 1.0);

        assertThrows(UnsupportedOperationException.class,
                () -> shipments.add(newShipment));
    }
}
