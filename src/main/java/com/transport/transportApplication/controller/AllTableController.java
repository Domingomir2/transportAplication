package com.transport.transportApplication.controller;

import com.transport.transportApplication.model.TableResponse;
import com.transport.transportApplication.service.AllTableService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/tables")
@ConditionalOnProperty(prefix = "bidea.datasource", name = "url")
public class AllTableController {

    private final AllTableService allTableService;

    public AllTableController(AllTableService allTableService) {
        this.allTableService = allTableService;
    }

    @GetMapping("/{tableName}")
    @Operation(operationId = "getTable", summary = "Consultar una tabla BIDEA paginada")
    public TableResponse<Map<String, Object>> getTable(
            @PathVariable String tableName,
            @RequestParam(defaultValue = "100") int numReg,
            @RequestParam(defaultValue = "1") int offset) {
        return allTableService.getTable(tableName, numReg, offset);
    }
}
