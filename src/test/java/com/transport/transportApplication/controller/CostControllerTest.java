package com.transport.transportApplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transport.transportApplication.model.CostRequest;
import com.transport.transportApplication.model.CostResponse;
import com.transport.transportApplication.service.CostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CostController.class)
class CostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockitoBean
    private CostService costService;

    @Test
    void calculateCost_Returns200AndJsonResponse() throws Exception {
        CostResponse mockResponse = new CostResponse(15.0, 7.5, 40.0, 62.5);
        when(costService.calculateCost(any(CostRequest.class))).thenReturn(mockResponse);

        CostRequest request = new CostRequest();
        request.setWeightKg(10.0);
        request.setDistanceKm(100.0);
        request.setServiceType("STANDARD");

        mockMvc.perform(post("/api/v1/costs/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalCost").value(62.5))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }
}