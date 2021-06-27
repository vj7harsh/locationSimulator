package com.locus.locationsimulator.model.entries;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiRequest {
    private Location origin;
    private Location destination;  
}
