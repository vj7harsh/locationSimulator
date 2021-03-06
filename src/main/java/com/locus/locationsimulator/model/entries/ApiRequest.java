package com.locus.locationsimulator.model.entries;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.maps.model.LatLng;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiRequest {
    private LatLng origin;
    private LatLng destination;  
}
