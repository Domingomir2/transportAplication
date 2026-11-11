package com.transport.transportApplication.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"summary", "elements"})
public record TableResponse<T>(Summary summary, List<T> elements) {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({"tableName", "status", "description", "page", "totalRecords"})
    public record Summary(
            String tableName,
            String status,
            String description,
            Integer page,
            Long pageRecords,
            long totalRecords,
            boolean lastPage) {
    }
}
