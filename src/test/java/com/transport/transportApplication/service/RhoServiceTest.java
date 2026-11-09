package com.transport.transportApplication.service;

import com.transport.transportApplication.model.Rho;
import com.transport.transportApplication.repository.DateFilteredTableRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

class RhoServiceTest {

    private final DateFilteredTableRepository repository = mock(DateFilteredTableRepository.class);
    private final RhoService rhoService = new RhoService(repository);

    @Test
    void getRhoUsesOffsetAsOneBasedPageNumber() {
        when(repository.countByDateRange(
                eq("RHO"),
                eq(LocalDate.of(2026, 1, 9)),
                eq(LocalDate.of(2026, 1, 9)))).thenReturn(200L);

        var response = rhoService.getRho("09012026", null, 10, 3);

        verify(repository).findByDateRange(
                eq("RHO"),
                eq("EXPEDICION"),
                eq(Rho.class),
                eq(LocalDate.of(2026, 1, 9)),
                eq(LocalDate.of(2026, 1, 9)),
                eq(3),
                eq(10));
        assertEquals("SUCCESS", response.summary().status());
        assertEquals(3, response.summary().page());
        assertEquals(200, response.summary().totalRecords());
        assertEquals("Consulta realizada correctamente", response.summary().description());
    }

    @Test
    void getRhoDescribesEmptyResult() {
        var response = rhoService.getRho("09012026", null, 100, 1);

        assertEquals(1, response.summary().page());
        assertEquals(0, response.summary().totalRecords());
        assertEquals("No se encontraron expediciones para el rango de fechas indicado",
                response.summary().description());
    }

    @Test
    void getRhoRejectsInvalidDate() {
        assertThrows(IllegalArgumentException.class,
                () -> rhoService.getRho("31022026", null, 100, 0));
    }

    @Test
    void getRhoRejectsReversedDateRange() {
        assertThrows(IllegalArgumentException.class,
                () -> rhoService.getRho("10012026", "09012026", 100, 0));
    }

    @Test
    void getRhoRejectsInvalidPageSize() {
        assertThrows(IllegalArgumentException.class,
                () -> rhoService.getRho("09012026", null, 0, 1));
    }

    @Test
    void getRhoRejectsPageZero() {
        assertThrows(IllegalArgumentException.class,
                () -> rhoService.getRho("09012026", null, 10, 0));
    }

    @Test
    void getRhoRejectsPageThatOverflowsRowBounds() {
        assertThrows(IllegalArgumentException.class,
                () -> rhoService.getRho("09012026", null, 100, Integer.MAX_VALUE));
    }
}
