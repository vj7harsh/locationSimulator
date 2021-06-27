package com.locus.locationsimulator.model.entries;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Location {
    private String longitude;
    private String latitude;

    public String getCoordinates(){
        return String.format("{},{}", longitude, latitude);
    }
}
