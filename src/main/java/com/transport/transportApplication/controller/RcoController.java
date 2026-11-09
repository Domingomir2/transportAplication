package com.transport.transportApplication.controller;

import com.transport.transportApplication.model.RcoResponse;
import com.transport.transportApplication.service.RcoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rco")
@ConditionalOnProperty(prefix = "bidea.datasource", name = "url")
public class RcoController {

    private final RcoService rcoService;

    public RcoController(RcoService rcoService) {
        this.rcoService = rcoService;
    }

    @GetMapping
    @Operation(operationId = "getRco", summary = "Consultar recogidas RCO por fecha")
    public RcoResponse getRco(
            @RequestParam String fromDate,
            @RequestParam(required = false) String toDate,
            @RequestParam(defaultValue = "100") int numReg,
            @RequestParam(defaultValue = "1") int offset) {
        return rcoService.getRco(fromDate, toDate, numReg, offset);
    }
}
