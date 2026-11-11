package com.transport.transportApplication.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transport.transportApplication.repository.RhoTablesRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RhoTablesServiceTest {

    private final RhoTablesRepository repository = mock(RhoTablesRepository.class);
    private final RhoTablesService service = new RhoTablesService(repository);

    @Test
    void getExpedicionCompletaReturnsRhoAndRelatedRows() throws Exception {
        Map<String, Object> rho = Map.of("EXPEDICION", 123);
        Map<String, Object> hes = Map.of("EXPEDICION", 123, "KEYHES", "H1");
        Map<String, Object> adr = Map.of("ADR_EXPEDICION", 123, "ADR_SECUENCIA", 1);
        when(repository.findAllAsMap(anyString(), anyString())).thenReturn(List.of());
        when(repository.findAllAsMap("RHO", "123")).thenReturn(List.of(rho));
        when(repository.findAllAsMap("HES", "123")).thenReturn(List.of(hes));
        when(repository.findAllAsMap("ADR", "ADR_EXPEDICION", "123")).thenReturn(List.of(adr));

        var response = service.getExpedicionCompleta("123");

        assertEquals(rho, response.rho());
        assertEquals(List.of(hes), response.detalle().hes());
        assertEquals(List.of(adr), response.detalle().adr());

        var objectMapper = new ObjectMapper();
        var json = objectMapper.readTree(objectMapper.writeValueAsString(response));
        assertTrue(json.path("rho").isObject());
        assertEquals(15, json.path("detalle").size());
        assertEquals(123, json.path("rho").path("EXPEDICION").intValue());
        assertEquals(1, json.path("detalle").path("adr").size());
        assertEquals(1, json.path("detalle").path("hes").size());
        assertFalse(json.path("detalle").has("ref_adu_info_imp"));
        assertEquals(0, json.path("detalle").path("ref_adu_info_mp").size());
        verify(repository).findAllAsMap("ADR", "ADR_EXPEDICION", "123");
        verify(repository).findAllAsMap("REF_ADU_INFO_IMP", "123");
    }

    @Test
    void getExpedicionCompletaRejectsMissingExpedition() {
        assertThrows(IllegalArgumentException.class, () -> service.getExpedicionCompleta(" "));
    }

    @Test
    void getExpedicionCompletaUsesInputWhenDominoReferenceIsNotFound() {
        when(repository.getRefDom("DOM-1")).thenReturn(null);
        when(repository.findAllAsMap("RHO", "DOM-1")).thenReturn(List.of());

        var response = service.getExpedicionCompleta("DOM-1");

        assertNull(response.rho());
        verify(repository).findAllAsMap("RHO", "DOM-1");
    }

    @Test
    void getExpedicionCompletaFallsBackWhenDominoLookupFails() {
        when(repository.getRefDom("DOM-2")).thenThrow(new IllegalStateException("lookup failed"));
        when(repository.findAllAsMap("RHO", "DOM-2")).thenReturn(List.of());

        service.getExpedicionCompleta("DOM-2");

        verify(repository).findAllAsMap("RHO", "DOM-2");
    }

    @Test
    void getTableNormalizesNameAndReturnsRowsAndCount() {
        Map<String, Object> row = Map.of("KEYHES", "H1");
        when(repository.supportsTable("HES")).thenReturn(true);
        when(repository.getRefDom("DOM-3")).thenReturn("123");
        when(repository.findAllAsMap("HES", "123")).thenReturn(List.of(row));
        when(repository.countAll("HES", "123")).thenReturn(3L);

        var response = service.getTable("hes", "DOM-3");

        assertEquals("HES", response.summary().tableName());
        assertEquals("SUCCESS", response.summary().status());
        assertEquals(3L, response.summary().totalRecords());
        assertEquals(List.of(row), response.elements());
        verify(repository).findAllAsMap("HES", "123");
    }

    @Test
    void getTableDescribesEmptyResultAndUsesInputWhenReferenceIsMissing() {
        when(repository.supportsTable("HES")).thenReturn(true);
        when(repository.getRefDom("DOM-4")).thenReturn(null);
        when(repository.findAllAsMap("HES", "DOM-4")).thenReturn(List.of());

        var response = service.getTable("HES", "DOM-4");

        assertEquals("ERROR", response.summary().status());
        assertEquals(0L, response.summary().pageRecords());
        assertEquals(0L, response.summary().totalRecords());
        assertEquals(List.of(), response.elements());
        verify(repository).countAll("HES", "DOM-4");
    }

    @Test
    void getTableRejectsInvalidDedicatedUnsupportedAndMissingInputs() {
        assertThrows(IllegalArgumentException.class, () -> service.getTable("bad-name", "123"));
        assertThrows(IllegalArgumentException.class, () -> service.getTable("RHO", "123"));
        when(repository.supportsTable("NO_SUCH_TABLE")).thenReturn(false);
        assertThrows(IllegalArgumentException.class, () -> service.getTable("NO_SUCH_TABLE", "123"));
        when(repository.supportsTable("HES")).thenReturn(true);
        assertThrows(IllegalArgumentException.class, () -> service.getTable("HES", ""));
        assertThrows(IllegalArgumentException.class, () -> service.getTable("HES", "  "));
        assertThrows(IllegalArgumentException.class, () -> service.getTable("HES", null));
    }
}
