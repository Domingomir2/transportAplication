package com.transport.transportApplication.controller;

import com.transport.transportApplication.model.RecogidaDTO;
import com.transport.transportApplication.model.TableResponse;
import com.transport.transportApplication.model.Rco;
import com.transport.transportApplication.service.RcoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/rco")
@ConditionalOnProperty(prefix = "bidea.datasource", name = "url")
public class RcoController {

    private final RcoService rcoService;

    public RcoController(RcoService rcoService) {
        this.rcoService = rcoService;
    }

    @GetMapping
    @Operation(operationId = "getOrders", summary = "Consultar paginada de recogidas RCO/RCH por fecha")
    public TableResponse<Rco> getRco(
            @RequestParam String fromDate,
            @RequestParam(required = false) String toDate,
            @RequestParam(defaultValue = "100") int numReg,
            @RequestParam(defaultValue = "1") int offset) {
        return rcoService.getRco(fromDate, toDate, numReg, offset);
    }

    @GetMapping("/recogidaCompleta/{recogida}")
    @Operation(operationId = "getCompleteOrder", summary = "Consultar una recogida y sus tablas relacionadas")
    public RecogidaDTO getRecogidaCompleta(@PathVariable String recogida) {
        return rcoService.getRecogidaCompleta(recogida);
    }

    @GetMapping("/cuentaRecogidas")
    @Operation(operationId = "getCountOrders", summary = "Devuelve el número de recogidas para un rango de fechas")
    public TableResponse<Map<String, Object>> getRcoCount(
            @RequestParam String fromDate,
            @RequestParam(required = false) String toDate) {
        return rcoService.getCountRco(fromDate, toDate);
    }
}
