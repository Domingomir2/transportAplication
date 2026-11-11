package com.transport.transportApplication.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({"summary", "elements"})
public record TableResponse<T>(Summary summary, List<T> elements) {

    @JsonPropertyOrder({"tableName", "status", "description", "page", "totalRecords"})
    public record Summary(
            String tableName,
            String status,
            String description,
            int page,
            long pageRecords,
            long totalRecords) {
    }
}
