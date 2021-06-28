package com.locus.locationsimulator.model.entries;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Location {
    private String latitude;
    private String longitude;

    public String getCoordinates(){
        return String.format("{},{}", latitude, longitude);
    }
}
