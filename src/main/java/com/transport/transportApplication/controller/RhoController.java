package com.transport.transportApplication.controller;

import com.transport.transportApplication.model.RhoResponse;
import com.transport.transportApplication.service.RhoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rho")
@ConditionalOnProperty(prefix = "bidea.datasource", name = "url")
public class RhoController {

    private final RhoService rhoService;

    public RhoController(RhoService rhoService) {
        this.rhoService = rhoService;
    }

    @GetMapping
    @Operation(operationId = "getRho", summary = "Consultar expediciones RHO por fecha")
    public RhoResponse getRho(
            @RequestParam String fromDate,
            @RequestParam(required = false) String toDate,
            @RequestParam(defaultValue = "100") int numReg,
            @RequestParam(defaultValue = "1") int offset) {
        return rhoService.getRho(fromDate, toDate, numReg, offset);
    }
}
