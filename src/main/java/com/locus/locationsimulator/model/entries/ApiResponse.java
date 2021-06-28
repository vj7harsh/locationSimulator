package com.locus.locationsimulator.model.entries;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.maps.model.LatLng;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {
    List<LatLng> pathPoints;
}
