package com.locus.locationsimulator.controller;

import java.util.List;

import com.locus.locationsimulator.manager.Manager;
import com.locus.locationsimulator.model.entries.ApiRequest;
import com.locus.locationsimulator.model.entries.ApiResponse;
import com.locus.locationsimulator.model.entries.Location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("route")
public class Controller {

    @Autowired
    private  Manager manager;

    @PostMapping(value = {"/directions"})
    private ResponseEntity<ApiResponse> getDirections(ApiRequest request){
        List<Location> locations = manager.getPathPoints(request.getOrigin(), request.getDestination());
        return ResponseEntity.ok().body(new ApiResponse(locations));
    }
       
}
