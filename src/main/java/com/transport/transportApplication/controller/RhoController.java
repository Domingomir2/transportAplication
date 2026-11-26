package com.transport.transportApplication.controller;

import com.transport.transportApplication.model.ExpedicionDTO;
import com.transport.transportApplication.model.TableResponse;
import com.transport.transportApplication.model.Rho;
import com.transport.transportApplication.service.RhoService;
import com.transport.transportApplication.service.RhoTablesService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/rho")
@ConditionalOnProperty(prefix = "bidea.datasource", name = "url")
public class RhoController {

    private final RhoService rhoService;
    private final RhoTablesService rhoTablesService;

    public RhoController(RhoService rhoService, RhoTablesService rhoTablesService) {
        this.rhoService = rhoService;
        this.rhoTablesService = rhoTablesService;
    }

    @GetMapping
    @Operation(operationId = "getShipments", summary = "Consulta paginada de expediciones RHO por fecha")
    public TableResponse<Rho> getRho(
            @RequestParam String fromDate,
            @RequestParam(required = false) String toDate,
            @RequestParam(defaultValue = "100") int numReg,
            @RequestParam(defaultValue = "1") int offset) {
        return rhoService.getRho(fromDate, toDate, numReg, offset);
    }

    @GetMapping("/expedicionCompleta/{expedicion}")
    @Operation(operationId = "getCompleteShipment", summary = "Consultar una expedición y sus tablas relacionadas")
    public ExpedicionDTO getExpedicionCompleta(@PathVariable String expedicion) {
        return rhoTablesService.getExpedicionCompleta(expedicion);
    }

    @GetMapping("/expedicionCompleta/{expedicion}/sql")
    @Operation(operationId = "downloadCompleteShipmentPostgresSql",
            summary = "Descargar las inserciones PostgreSQL de una expedición y sus tablas relacionadas")
    public ResponseEntity<byte[]> descargarExpedicionCompletaSql(@PathVariable String expedicion) {
        byte[] content = rhoTablesService.getExpedicionCompletaSql(expedicion)
                .getBytes(StandardCharsets.UTF_8);
        String safeExpedicion = expedicion.replaceAll("[^A-Za-z0-9_-]", "_");
        return ResponseEntity.ok()
                .contentType(new MediaType("text", "sql", StandardCharsets.UTF_8))
                .header(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment()
                        .filename("expedicion_" + safeExpedicion + ".sql", StandardCharsets.UTF_8)
                        .build()
                        .toString())
                .body(content);
    }

    @GetMapping("/expedicionCompletaFecha/sql")
    @Operation(operationId = "downloadDateFilteredShipmentsPostgresSql",
            summary = "Descargar las inserciones PostgreSQL de las expediciones de un rango de fechas")
    public ResponseEntity<StreamingResponseBody> descargarExpedicionCompletaSqlFecha(
            @RequestParam String fromDate,
            @RequestParam(required = false) String toDate,
            @RequestParam(defaultValue = "100") int numReg,
            @RequestParam(defaultValue = "1") int offset) {
        StreamingResponseBody content = outputStream -> {
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
            rhoService.writeExpedicionCompletaSqlFechas(fromDate, toDate, numReg, offset, writer);
            writer.flush();
        };
        String safeToDate = toDate == null ? fromDate : toDate;
        return ResponseEntity.ok()
                .contentType(new MediaType("text", "sql", StandardCharsets.UTF_8))
                .header(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment()
                        .filename("Expediciones_Desde_Fecha_" + fromDate + "_Hasta_Fecha_"
                                + safeToDate + "_pagina_" + offset + ".sql", StandardCharsets.UTF_8)
                        .build()
                        .toString())
                .body(content);
    }

    @GetMapping("/{tableName}/{expedicion}")
    @Operation(operationId = "getShipmentTable", summary = "Consultar una tabla relacionada con la expedición")
    public TableResponse<Map<String, Object>> getTable(
            @PathVariable String tableName,
            @PathVariable String expedicion) {
        return rhoTablesService.getTable(tableName, expedicion);
    }

    @GetMapping("/cuentaExpediciones")
    @Operation(operationId = "getCountShipments", summary = "Devuelve el número de expediciones para un rango de fechas")
    public TableResponse<Map<String, Object>> getRcoCount(
            @RequestParam String fromDate,
            @RequestParam(required = false) String toDate) {
        return rhoService.getCountRho(fromDate, toDate);
    }
}
