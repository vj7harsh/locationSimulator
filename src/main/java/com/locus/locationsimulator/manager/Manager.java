package com.locus.locationsimulator.manager;

import java.util.List;

import com.google.maps.model.LatLng;
import com.locus.locationsimulator.helpers.GoogleHelper;
import com.locus.locationsimulator.model.entries.Step;
import com.locus.locationsimulator.model.exceptions.GoogleException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Manager {

    @Autowired
    private GoogleHelper googleHelper;
    
    public List<LatLng> getPathPoints(LatLng source, LatLng destination) throws GoogleException{
        List<Step> steps =  googleHelper.getSteps(source, destination);
        // get valid points from steps
        return null;
    }
    
}
