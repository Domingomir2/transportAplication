package com.transport.transportApplication.service;

import com.transport.transportApplication.repository.AllTableRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AllTableServiceTest {

    private final AllTableRepository repository = mock(AllTableRepository.class);
    private final AllTableService service = new AllTableService(repository);

    @Test
    void getTableReturnsGenericPageAndSummary() {
        when(repository.supportsTable("CLI")).thenReturn(true);
        when(repository.findAllAsMap("CLI", 2, 10)).thenReturn(List.of(Map.of("KEYCLI", "C1")));
        when(repository.countAll("CLI")).thenReturn(15L);

        var response = service.getTable("cli", 10, 2);

        verify(repository).findAllAsMap("CLI", 2, 10);
        verify(repository).countAll("CLI");
        assertEquals("CLI", response.summary().tableName());
        assertEquals("SUCCESS", response.summary().status());
        assertEquals(2, response.summary().page());
        assertEquals(15, response.summary().totalRecords());
        assertEquals(List.of(Map.of("KEYCLI", "C1")), response.elements());
    }

    @Test
    void getTableRejectsTablesWithDedicatedServices() {
        assertThrows(IllegalArgumentException.class, () -> service.getTable("RHO", 10, 1));
        assertThrows(IllegalArgumentException.class, () -> service.getTable("RCO", 10, 1));
    }

    @Test
    void getTableRejectsTablesWithoutManifest() {
        when(repository.supportsTable("NOT_A_TABLE")).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> service.getTable("NOT_A_TABLE", 10, 1));
    }

    @Test
    void getTableRejectsInvalidPage() {
        when(repository.supportsTable("CLI")).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> service.getTable("CLI", 0, 1));
        assertThrows(IllegalArgumentException.class, () -> service.getTable("CLI", 10, 0));
    }
}
