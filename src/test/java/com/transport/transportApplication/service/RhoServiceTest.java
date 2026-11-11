package com.transport.transportApplication.service;

import com.transport.transportApplication.model.Rho;
import com.transport.transportApplication.repository.DateFilteredTableRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

class RhoServiceTest {

    private final DateFilteredTableRepository repository = mock(DateFilteredTableRepository.class);
    private final RhoService rhoService = new RhoService(repository);

    @Test
    void getRhoUsesOffsetAsOneBasedPageNumber() {
        when(repository.findByDateRange(
                "RHO",
                "EXPEDICION",
                Rho.class,
                LocalDate.of(2026, 1, 9),
                LocalDate.of(2026, 1, 9),
                3,
                10)).thenReturn(List.of(new Rho()));
        when(repository.countByDateRange(
                "RHO",
                LocalDate.of(2026, 1, 9),
                LocalDate.of(2026, 1, 9))).thenReturn(200L);

        var response = rhoService.getRho("09012026", null, 10, 3);

        verify(repository).findByDateRange(
                "RHO",
                "EXPEDICION",
                Rho.class,
                LocalDate.of(2026, 1, 9),
                LocalDate.of(2026, 1, 9),
                3,
                10);
        assertEquals("SUCCESS", response.summary().status());
        assertEquals("RHO", response.summary().tableName());
        assertEquals(3, response.summary().page());
        assertEquals(200, response.summary().totalRecords());
        assertEquals("Consulta realizada correctamente", response.summary().description());
        assertEquals(1, response.elements().size());
    }

    @Test
    void getRhoDescribesEmptyResult() {
        var response = rhoService.getRho("09012026", null, 100, 1);

        assertEquals(1, response.summary().page());
        assertEquals("RHO", response.summary().tableName());
        assertEquals("ERROR", response.summary().status());
        assertEquals(0, response.summary().totalRecords());
        assertEquals("No se encontraron expediciones para el rango de fechas en la página indicada",
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

    @Test
    void getRhoRejectsMissingAndMalformedDates() {
        assertThrows(IllegalArgumentException.class,
                () -> rhoService.getRho(null, null, 10, 1));
        assertThrows(IllegalArgumentException.class,
                () -> rhoService.getRho("9-1-2026", null, 10, 1));
        verifyNoInteractions(repository);
    }

    @Test
    void getRhoAcceptsDateRangeAndReturnsLastPartialPage() {
        LocalDate start = LocalDate.of(2026, 1, 9);
        LocalDate end = LocalDate.of(2026, 1, 10);
        when(repository.findByDateRange(
                "RHO", "EXPEDICION", Rho.class, start, end, 2, 10))
                .thenReturn(List.of(new Rho(), new Rho()));
        when(repository.countByDateRange("RHO", start, end)).thenReturn(12L);

        var response = rhoService.getRho("09012026", "10012026", 10, 2);

        assertEquals(2L, response.summary().pageRecords());
        assertEquals(12L, response.summary().totalRecords());
        assertTrue(response.summary().lastPage());
        verify(repository).findByDateRange(
                "RHO", "EXPEDICION", Rho.class, start, end, 2, 10);
    }

    @Test
    void getCountRhoReturnsSuccessAndEmptyStatuses() {
        LocalDate date = LocalDate.of(2026, 1, 9);
        when(repository.countByDateRange(List.of("RHO"), "EXPEDICION", date, date))
                .thenReturn(4L, 0L);

        var found = rhoService.getCountRho("09012026", null);
        var empty = rhoService.getCountRho("09012026", "09012026");

        assertEquals("SUCCESS", found.summary().status());
        assertEquals(4L, found.summary().totalRecords());
        assertEquals("ERROR", empty.summary().status());
        assertEquals(0L, empty.summary().totalRecords());
        assertNull(empty.elements());
    }

    @Test
    void getCountRhoRejectsInvalidDatesAndRanges() {
        assertThrows(IllegalArgumentException.class,
                () -> rhoService.getCountRho("10012026", "09012026"));
        assertThrows(IllegalArgumentException.class,
                () -> rhoService.getCountRho("31022026", null));
        verifyNoInteractions(repository);
    }
}
