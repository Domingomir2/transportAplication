package com.transport.transportApplication.controller;

import com.transport.transportApplication.model.CostRequest;
import com.transport.transportApplication.model.CostResponse;
import com.transport.transportApplication.service.CostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/costs")
@CrossOrigin(origins = "${app.cors.allowed-origin}")
public class CostController {

    private final CostService costService;

    public CostController(CostService costService) {
        this.costService = costService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<CostResponse> calculateCost(@RequestBody CostRequest request) {
        CostResponse response = costService.calculateCost(request);
        return ResponseEntity.ok(response);
    }
}
