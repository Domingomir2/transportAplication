package com.transport.transportApplication.controller;

import com.transport.transportApplication.exception.GlobalExceptionHandler;
import com.transport.transportApplication.model.ExpedicionDTO;
import com.transport.transportApplication.model.Rho;
import com.transport.transportApplication.model.TableResponse;
import com.transport.transportApplication.service.RhoService;
import com.transport.transportApplication.service.RhoTablesService;
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
        controllers = RhoController.class,
        properties = "bidea.datasource.url=jdbc:oracle:thin:@localhost:1521/FORBID")
@Import(GlobalExceptionHandler.class)
class RhoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RhoService rhoService;

    @MockitoBean
    private RhoTablesService rhoTablesService;

    @Test
    void getRhoUsesDefaultPaginationAndRequiresFromDate() throws Exception {
        when(rhoService.getRho("09012026", null, 100, 1))
                .thenReturn(new TableResponse<>(
                        new TableResponse.Summary(
                                "RHO", "SUCCESS", "ok", 1, 1L, 1L, true),
                        List.of(new Rho())));

        mockMvc.perform(get("/api/v1/rho").param("fromDate", "09012026"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.summary.tableName").value("RHO"))
                .andExpect(jsonPath("$.summary.page").value(1))
                .andExpect(jsonPath("$.elements").isArray());
        verify(rhoService).getRho("09012026", null, 100, 1);

        mockMvc.perform(get("/api/v1/rho"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getRhoPassesDateRangeAndExplicitPagination() throws Exception {
        when(rhoService.getRho("09012026", "10012026", 20, 3))
                .thenReturn(new TableResponse<>(
                        new TableResponse.Summary(
                                "RHO", "SUCCESS", "ok", 3, 1L, 41L, true),
                        List.of(new Rho())));

        mockMvc.perform(get("/api/v1/rho")
                        .param("fromDate", "09012026")
                        .param("toDate", "10012026")
                        .param("numReg", "20")
                        .param("offset", "3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.summary.page").value(3))
                .andExpect(jsonPath("$.summary.totalRecords").value(41));
        verify(rhoService).getRho("09012026", "10012026", 20, 3);
    }

    @Test
    void getCompleteShipmentReturnsShipmentAndRelatedTables() throws Exception {
        ExpedicionDTO response = new ExpedicionDTO(
                Map.of("EXPEDICION", 123),
                new ExpedicionDTO.Detalle(
                        List.of(), List.of(), List.of(), List.of(), List.of(),
                        List.of(), List.of(), List.of(), List.of(), List.of(),
                        List.of(), List.of(), List.of(), List.of(), List.of()));
        when(rhoTablesService.getExpedicionCompleta("123")).thenReturn(response);

        mockMvc.perform(get("/api/v1/rho/expedicionCompleta/123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rho.EXPEDICION").value(123))
                .andExpect(jsonPath("$.detalle.hes").isArray());
        verify(rhoTablesService).getExpedicionCompleta("123");
    }

    @Test
    void getShipmentTablePassesPathParametersAndMapsInvalidRequestsToBadRequest() throws Exception {
        when(rhoTablesService.getTable("hes", "123")).thenReturn(
                new TableResponse<>(
                        new TableResponse.Summary(
                                "HES", "SUCCESS", "ok", 1, 1L, 1L, true),
                        List.of(Map.of("KEYHES", "H1"))));

        mockMvc.perform(get("/api/v1/rho/hes/123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.summary.tableName").value("HES"))
                .andExpect(jsonPath("$.elements[0].KEYHES").value("H1"));
        verify(rhoTablesService).getTable("hes", "123");

        when(rhoTablesService.getTable("invalid", "123"))
                .thenThrow(new IllegalArgumentException("tabla no permitida"));
        mockMvc.perform(get("/api/v1/rho/invalid/123"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("tabla no permitida"));
    }

    @Test
    void getShipmentCountRequiresFromDateAndReturnsCountSummary() throws Exception {
        when(rhoService.getCountRho("09012026", null)).thenReturn(
                new TableResponse<>(
                        new TableResponse.Summary(
                                "RHO", "SUCCESS", "Datos encontrados", null, null, 7L, true),
                        null));

        mockMvc.perform(get("/api/v1/rho/cuentaExpediciones").param("fromDate", "09012026"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.summary.totalRecords").value(7));
        verify(rhoService).getCountRho("09012026", null);

        mockMvc.perform(get("/api/v1/rho/cuentaExpediciones"))
                .andExpect(status().isBadRequest());
    }
}
