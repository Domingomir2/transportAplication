package com.transport.transportApplication.service;

import com.transport.transportApplication.model.Rco;
import com.transport.transportApplication.repository.DateFilteredTableRepository;
import com.transport.transportApplication.repository.RhoTablesRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class RcoServiceTest {

    private final DateFilteredTableRepository dateRepository = mock(DateFilteredTableRepository.class);
    private final RhoTablesRepository tablesRepository = mock(RhoTablesRepository.class);
    private final RcoService service = new RcoService(dateRepository, tablesRepository);

    @Test
    void getRcoCombinesCurrentAndHistoricalRowsAndKeepsLatestDuplicate() {
        LocalDate date = LocalDate.of(2026, 1, 9);
        Rco current = rco(10L, "current");
        Rco historicalDuplicate = rco(10L, "historical");
        Rco historicalOnly = rco(11L, "historical-only");
        when(dateRepository.findByDateRange(
                "RCO", "KEYRCO", Rco.class, date, date, 2, 10))
                .thenReturn(new ArrayList<>(List.of(current)));
        when(dateRepository.findByDateRange(
                "RCH", "KEYRCO", Rco.class, date, date, 2, 10))
                .thenReturn(new ArrayList<>(List.of(historicalDuplicate, historicalOnly)));
        when(dateRepository.countByDateRange(List.of("RCO", "RCH"), "KEYRCO", date, date))
                .thenReturn(15L);

        var response = service.getRco("09012026", null, 10, 2);

        assertEquals("SUCCESS", response.summary().status());
        assertEquals("RCO", response.summary().tableName());
        assertEquals(2, response.summary().page());
        assertEquals(15L, response.summary().totalRecords());
        assertEquals(2L, response.summary().pageRecords());
        assertEquals(2, response.elements().size());
        assertEquals("historical", response.elements().stream()
                .filter(row -> row.getKeyrco().equals(10L))
                .findFirst().orElseThrow().getNombre());
        assertTrue(response.summary().lastPage());
        verify(dateRepository).findByDateRange(
                "RCO", "KEYRCO", Rco.class, date, date, 2, 10);
        verify(dateRepository).findByDateRange(
                "RCH", "KEYRCO", Rco.class, date, date, 2, 10);
    }

    @Test
    void getRcoReturnsEmptyPageStatus() {
        LocalDate date = LocalDate.of(2026, 1, 9);
        when(dateRepository.findByDateRange(
                "RCO", "KEYRCO", Rco.class, date, date, 1, 10))
                .thenReturn(new ArrayList<>());
        when(dateRepository.findByDateRange(
                "RCH", "KEYRCO", Rco.class, date, date, 1, 10))
                .thenReturn(new ArrayList<>());
        when(dateRepository.countByDateRange(List.of("RCO", "RCH"), "KEYRCO", date, date))
                .thenReturn(0L);

        var response = service.getRco("09012026", null, 10, 1);

        assertEquals("ERROR", response.summary().status());
        assertEquals(0L, response.summary().totalRecords());
        assertEquals(0L, response.summary().pageRecords());
        assertEquals(List.of(), response.elements());
    }

    @Test
    void getRcoRejectsInvalidDatesAndPagination() {
        assertThrows(IllegalArgumentException.class,
                () -> service.getRco("31022026", null, 10, 1));
        assertThrows(IllegalArgumentException.class,
                () -> service.getRco("10012026", "09012026", 10, 1));
        assertThrows(IllegalArgumentException.class,
                () -> service.getRco("09012026", null, 0, 1));
        assertThrows(IllegalArgumentException.class,
                () -> service.getRco("09012026", null, 10, 0));
        assertThrows(IllegalArgumentException.class,
                () -> service.getRco("09012026", null, 100, Integer.MAX_VALUE));
        verifyNoInteractions(dateRepository, tablesRepository);
    }

    @Test
    void getCountRcoCountsDistinctRecordsAcrossBothTables() {
        LocalDate start = LocalDate.of(2026, 1, 9);
        LocalDate end = LocalDate.of(2026, 1, 10);
        when(dateRepository.countByDateRange(
                List.of("RCO", "RCH"), "KEYRCO", start, end)).thenReturn(8L, 0L);

        var found = service.getCountRco("09012026", "10012026");
        var empty = service.getCountRco("09012026", "10012026");

        assertEquals("RCO / RCH", found.summary().tableName());
        assertEquals("SUCCESS", found.summary().status());
        assertEquals(8L, found.summary().totalRecords());
        assertEquals("ERROR", empty.summary().status());
        assertEquals(0L, empty.summary().totalRecords());
        verify(dateRepository, times(2))
                .countByDateRange(List.of("RCO", "RCH"), "KEYRCO", start, end);
    }

    @Test
    void getCountRcoRejectsInvalidDateRange() {
        assertThrows(IllegalArgumentException.class,
                () -> service.getCountRco("09012026", "08012026"));
        assertThrows(IllegalArgumentException.class,
                () -> service.getCountRco(null, null));
        verifyNoInteractions(dateRepository, tablesRepository);
    }

    @Test
    void getRecogidaCompletaCombinesRowsAndRemovesDuplicateKeys() {
        Map<String, Object> current = Map.of("KEYRCO", 10L, "NOMBRE", "current");
        Map<String, Object> historyDuplicate = Map.of("KEYRCO", 10L, "NOMBRE", "history");
        Map<String, Object> historyOnly = Map.of("KEYRCO", 11L, "NOMBRE", "older");
        Map<String, Object> line = Map.of("KEYRCO", 10L, "LINEA", 1);
        when(tablesRepository.findAllAsMap("RCO", "keyrco", "10"))
                .thenReturn(new ArrayList<>(List.of(current)));
        when(tablesRepository.findAllAsMap("RCH", "keyrco", "10"))
                .thenReturn(new ArrayList<>(List.of(historyDuplicate, historyOnly)));
        when(tablesRepository.findAllAsMap("RCL", "keyrco", "10")).thenReturn(List.of(line));
        when(tablesRepository.findAllAsMap("RCO_INCIDENCIAS", "keyrco", "10")).thenReturn(List.of());
        when(tablesRepository.findAllAsMap("RCO_REF", "keyrco", "10")).thenReturn(List.of());

        var response = service.getRecogidaCompleta("10");

        assertEquals(current, response.rco());
        assertEquals(List.of(line), response.detalle().Rcl());
        verify(tablesRepository).findAllAsMap("RCO", "keyrco", "10");
        verify(tablesRepository).findAllAsMap("RCH", "keyrco", "10");
    }

    @Test
    void getRecogidaCompletaAllowsNoMainRowAndRejectsMissingKey() {
        when(tablesRepository.findAllAsMap("RCO", "keyrco", "missing"))
                .thenReturn(new ArrayList<>());
        when(tablesRepository.findAllAsMap("RCH", "keyrco", "missing"))
                .thenReturn(new ArrayList<>());

        var response = service.getRecogidaCompleta("missing");

        assertNull(response.rco());
        assertThrows(IllegalArgumentException.class, () -> service.getRecogidaCompleta(" "));
    }

    private Rco rco(Long key, String name) {
        Rco row = new Rco();
        row.setKeyrco(key);
        row.setNombre(name);
        return row;
    }
}
