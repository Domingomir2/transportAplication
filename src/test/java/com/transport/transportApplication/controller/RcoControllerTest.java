package com.transport.transportApplication.controller;

import com.transport.transportApplication.exception.GlobalExceptionHandler;
import com.transport.transportApplication.model.Rco;
import com.transport.transportApplication.model.RecogidaDTO;
import com.transport.transportApplication.model.TableResponse;
import com.transport.transportApplication.service.RcoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        controllers = RcoController.class,
        properties = "bidea.datasource.url=jdbc:oracle:thin:@localhost:1521/FORBID")
@Import(GlobalExceptionHandler.class)
class RcoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RcoService rcoService;

    @Test
    void getRcoUsesDefaultPaginationAndRequiresFromDate() throws Exception {
        when(rcoService.getRco("09012026", null, 100, 1))
                .thenReturn(new TableResponse<>(
                        new TableResponse.Summary(
                                "RCO", "SUCCESS", "ok", 1, 1L, 1L, true),
                        List.of(new Rco())));

        mockMvc.perform(get("/api/v1/rco").param("fromDate", "09012026"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.summary.tableName").value("RCO"))
                .andExpect(jsonPath("$.summary.page").value(1))
                .andExpect(jsonPath("$.elements").isArray());
        verify(rcoService).getRco("09012026", null, 100, 1);

        mockMvc.perform(get("/api/v1/rco"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getRcoPassesExplicitDateRangeAndPagination() throws Exception {
        when(rcoService.getRco("09012026", "10012026", 20, 2))
                .thenReturn(new TableResponse<>(
                        new TableResponse.Summary(
                                "RCO", "SUCCESS", "ok", 2, 1L, 3L, true),
                        List.of(new Rco())));

        mockMvc.perform(get("/api/v1/rco")
                        .param("fromDate", "09012026")
                        .param("toDate", "10012026")
                        .param("numReg", "20")
                        .param("offset", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.summary.page").value(2));
        verify(rcoService).getRco("09012026", "10012026", 20, 2);
    }

    @Test
    void getCompleteOrderReturnsPickupAndRelatedTables() throws Exception {
        RecogidaDTO response = new RecogidaDTO(
                Map.of("KEYRCO", 123),
                new RecogidaDTO.Detalle(List.of(), List.of(), List.of()));
        when(rcoService.getRecogidaCompleta("123")).thenReturn(response);

        mockMvc.perform(get("/api/v1/rco/recogidaCompleta/123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rco.KEYRCO").value(123))
                .andExpect(jsonPath("$.detalle.rcl").isArray())
                .andExpect(jsonPath("$.detalle.rco_incidencias").isArray());
        verify(rcoService).getRecogidaCompleta("123");
    }

    @Test
    void getPickupCountRequiresFromDateAndReturnsCountSummary() throws Exception {
        when(rcoService.getCountRco("09012026", "10012026")).thenReturn(
                new TableResponse<>(
                        new TableResponse.Summary(
                                "RCO / RCH", "SUCCESS", "Datos encontrados", null, null, 12L, true),
                        null));

        mockMvc.perform(get("/api/v1/rco/cuentaRecogidas")
                        .param("fromDate", "09012026")
                        .param("toDate", "10012026"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.summary.tableName").value("RCO / RCH"))
                .andExpect(jsonPath("$.summary.totalRecords").value(12));
        verify(rcoService).getCountRco("09012026", "10012026");

        mockMvc.perform(get("/api/v1/rco/cuentaRecogidas"))
                .andExpect(status().isBadRequest());
    }
}
