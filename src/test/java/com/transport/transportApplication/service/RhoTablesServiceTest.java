package com.transport.transportApplication.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transport.transportApplication.repository.RhoTablesRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
}
