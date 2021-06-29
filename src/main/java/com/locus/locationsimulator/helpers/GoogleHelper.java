package com.locus.locationsimulator.helpers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.LatLng;
import com.locus.locationsimulator.model.entries.Step;
import com.locus.locationsimulator.model.exceptions.GoogleException;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class GoogleHelper {

    private final GeoApiContext geoApiContext;

    public GoogleHelper(){
        this.geoApiContext = new GeoApiContext.Builder().apiKey("AIzaSyAEQvKUVouPDENLkQlCF6AAap1Ze-6zMos").connectTimeout(1, TimeUnit.SECONDS).maxRetries(3).build();
    }

    public List<LatLng> getPolylinePoints(LatLng source, LatLng destination) throws GoogleException{
        try {
            DirectionsResult result = DirectionsApi.getDirections(geoApiContext, source.toUrlValue(), destination.toUrlValue()).await();
            validateResponse(result);
            return getCompletePolylinePoints(result);
        } catch (ApiException | InterruptedException | IOException e) {
            log.error(e.getMessage());
            throw new GoogleException("Couldn't get response from google");
        }
    }

    public List<Step> getSteps(LatLng source, LatLng destination) throws GoogleException {
        try {
            DirectionsResult result = DirectionsApi.getDirections(geoApiContext, source.toUrlValue(), destination.toUrlValue()).await();
            validateResponse(result);
            return getStepsFromResponse(result);
        } catch (ApiException | InterruptedException | IOException e) {
            log.error(e.getMessage());
            throw new GoogleException("Couldn't get response from google");
        }
    }

    private List<Step> getStepsFromResponse(DirectionsResult result){
        DirectionsStep[] directionsSteps = result.routes[0].legs[0].steps;
        List<Step> steps = new ArrayList<>();
        for(DirectionsStep directionsStep : directionsSteps){
            Step step = new Step();
            step.setStepDist((int)directionsStep.distance.inMeters);
            step.setStepStrt(directionsStep.startLocation);
            step.setStepEnd(directionsStep.endLocation);
            step.setPointsOnPolyLine(directionsStep.polyline.decodePath());
            steps.add(step);
        }
        return steps;
    }

    private List<LatLng> getCompletePolylinePoints(DirectionsResult result){
        return result.routes[0].overviewPolyline.decodePath();
    }
    
    private void validateResponse(DirectionsResult result){
        
    }

}
