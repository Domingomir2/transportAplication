package com.transport.transportApplication.controller;

import com.transport.transportApplication.model.RecogidaDTO;
import com.transport.transportApplication.model.TableResponse;
import com.transport.transportApplication.model.Rco;
import com.transport.transportApplication.service.RcoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.*;

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
    public TableResponse<Rco> getRco(
            @RequestParam String fromDate,
            @RequestParam(required = false) String toDate,
            @RequestParam(defaultValue = "100") int numReg,
            @RequestParam(defaultValue = "1") int offset) {
        return rcoService.getRco(fromDate, toDate, numReg, offset);
    }

    @GetMapping("/recogidaCompleta/{recogida}")
    @Operation(operationId = "getRecogidaCompleta", summary = "Consultar una recogida y sus tablas relacionadas")
    public RecogidaDTO getRecogidaCompleta(@PathVariable String recogida) {
        return rcoService.getRecogidaCompleta(recogida);
    }
}
