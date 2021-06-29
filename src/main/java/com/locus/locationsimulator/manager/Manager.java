package com.locus.locationsimulator.manager;

import java.util.ArrayList;
import java.util.List;
import com.google.maps.model.LatLng;
import com.locus.locationsimulator.helpers.GoogleHelper;
import com.locus.locationsimulator.model.entries.Step;
import com.locus.locationsimulator.model.exceptions.GoogleException;
import com.locus.locationsimulator.utils.GeometricUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class Manager {

    @Autowired
    private GoogleHelper googleHelper;

    private static final double contiguosDist = 50; // in metres
    
    public List<LatLng> getPathPoints(LatLng source, LatLng destination) throws GoogleException{
        List<Step> steps =  googleHelper.getSteps(source, destination);
        List<LatLng> pointsList = new ArrayList<>();
        for(int i=0; i<steps.size(); i++){
            addEquidistPointsInCurrStep(steps.get(i).getPointsOnPolyLine(), pointsList);
        }
        for(int j=1; j<pointsList.size(); j++){
            System.out.println(pointsList.get(j).lat + "," + pointsList.get(j).lng + ",");
        } 
        List<LatLng> polylinePoints = googleHelper.getPolylinePoints(source, destination);
        System.out.println("*********************************************************");
        pointsList = new ArrayList<>();
        addEquidistPointsInCurrStep(polylinePoints, pointsList);
        for(int j=1; j<pointsList.size(); j++){
            System.out.println(pointsList.get(j).lat + "," + pointsList.get(j).lng + ",");
        }
        return pointsList;
    }

    private void addEquidistPointsInCurrStep(List<LatLng> pointsInStep, List<LatLng> pointsList){
        LatLng currPoint = pointsInStep.get(0), nextPoint;
        pointsList.add(currPoint);
        double distanceToNextPoint, distanceCovered = 0;
        int i = 1;
        while(i<pointsInStep.size()){
            nextPoint = pointsInStep.get(i);
            distanceToNextPoint = GeometricUtil.distBtwInMts(currPoint, nextPoint);
            if(distanceCovered + distanceToNextPoint < contiguosDist){
                i++;
                distanceCovered += distanceToNextPoint;
                currPoint = nextPoint;
            } else {
                currPoint = GeometricUtil.getInterpolatedPoint(currPoint, contiguosDist - distanceCovered, nextPoint);
                pointsList.add(currPoint);
                distanceCovered = 0;
            }
        }
    }

}
