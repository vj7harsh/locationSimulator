package com.locus.locationsimulator.model.entries;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.maps.model.LatLng;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Step {
    private LatLng stepStrt;
    private LatLng stepEnd;
    private int stepDist;
    private List<LatLng> pointsOnPolyLine;
}
