package com.transport.transportApplication.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({"summary", "orders"})
public record RcoResponse(Summary summary, List<Rco> orders) {

    @JsonPropertyOrder({"status", "description", "page", "totalRecords"})
    public record Summary(String status, String description, int page, long totalRecords) {
    }
}
