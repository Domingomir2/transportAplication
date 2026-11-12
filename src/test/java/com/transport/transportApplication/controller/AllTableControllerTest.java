package com.transport.transportApplication.controller;

import com.transport.transportApplication.exception.GlobalExceptionHandler;
import com.transport.transportApplication.model.TableResponse;
import com.transport.transportApplication.service.AllTableService;
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
        controllers = AllTableController.class,
        properties = "bidea.datasource.url=jdbc:oracle:thin:@localhost:1521/FORBID")
@Import(GlobalExceptionHandler.class)
class AllTableControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AllTableService allTableService;

    @Test
    void getTableUsesDefaultPaginationAndReturnsRows() throws Exception {
        when(allTableService.getTable("cli", 100, 1)).thenReturn(
                new TableResponse<>(
                        new TableResponse.Summary(
                                "CLI", "SUCCESS", "ok", 1, 1L, 1L, true),
                        List.of(Map.of("KEYCLI", "C1"))));

        mockMvc.perform(get("/api/v1/tables/cli"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.summary.tableName").value("CLI"))
                .andExpect(jsonPath("$.summary.page").value(1))
                .andExpect(jsonPath("$.elements[0].KEYCLI").value("C1"));
        verify(allTableService).getTable("cli", 100, 1);
    }

    @Test
    void getTablePassesExplicitPaginationAndReturnsBadRequestForInvalidTable() throws Exception {
        when(allTableService.getTable("hes", 25, 4)).thenReturn(
                new TableResponse<>(
                        new TableResponse.Summary(
                                "HES", "SUCCESS", "ok", 4, 1L, 76L, true),
                        List.of(Map.of("KEYHES", "H1"))));

        mockMvc.perform(get("/api/v1/tables/hes")
                        .param("numReg", "25")
                        .param("offset", "4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.summary.page").value(4))
                .andExpect(jsonPath("$.summary.totalRecords").value(76));
        verify(allTableService).getTable("hes", 25, 4);

        when(allTableService.getTable("bad-name", 100, 1))
                .thenThrow(new IllegalArgumentException("nombre de tabla inválido"));
        mockMvc.perform(get("/api/v1/tables/bad-name"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("nombre de tabla inválido"));
    }

    @Test
    void getTableRejectsNonNumericPagination() throws Exception {
        mockMvc.perform(get("/api/v1/tables/cli").param("numReg", "invalid"))
                .andExpect(status().isBadRequest());
    }
}
