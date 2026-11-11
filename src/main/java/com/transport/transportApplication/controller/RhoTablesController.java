package com.transport.transportApplication.controller;

import com.transport.transportApplication.model.TableResponse;
import com.transport.transportApplication.model.ExpedicionDTO;
import com.transport.transportApplication.service.RhoTablesService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/rhoFilter")
@ConditionalOnProperty(prefix = "bidea.datasource", name = "url")
public class RhoTablesController {

    private final RhoTablesService rhoTablesService;

    public RhoTablesController(RhoTablesService rhoTablesService) {
        this.rhoTablesService = rhoTablesService;
    }

    @GetMapping("/expedicionCompleta/{expedicion}")
    @Operation(operationId = "getExpedicionCompleta", summary = "Consultar una expedición y sus tablas relacionadas")
    public ExpedicionDTO getExpedicionCompleta(@PathVariable String expedicion) {
        return rhoTablesService.getExpedicionCompleta(expedicion);
    }

    @GetMapping("/{tableName}/{expedicion}")
    @Operation(operationId = "getTable", summary = "Consultar una tabla BIDEA paginada")
    public TableResponse<Map<String, Object>> getTable(
            @PathVariable String tableName,
            @PathVariable String expedicion) {
        return rhoTablesService.getTable(tableName, expedicion);
    }

    @GetMapping("/{tableName}/ref/{refDom}")
    @Operation(operationId = "getTableByRef", summary = "Consultar una tabla por la referencia Domino de la expedición")
    public TableResponse<Map<String, Object>> getTableByRef(
            @PathVariable String tableName,
            @PathVariable String refDom) {
        return rhoTablesService.getTableByRef(tableName, refDom);
    }
}
