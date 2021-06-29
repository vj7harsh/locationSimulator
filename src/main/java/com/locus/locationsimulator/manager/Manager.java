package com.locus.locationsimulator.manager;

import java.util.ArrayList;
import java.util.List;
import com.google.maps.model.LatLng;
import com.locus.locationsimulator.helpers.GoogleHelper;
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
        List<LatLng> polylinePoints = googleHelper.getPolylinePoints(source, destination);
        return getEquiDistPointsAlongPath(polylinePoints);
    }

    private List<LatLng> getEquiDistPointsAlongPath(List<LatLng> pointsOnPolyLine){
        List<LatLng> equiDistPoints = new ArrayList<>();
        LatLng currPoint = pointsOnPolyLine.get(0), nextPoint;
        equiDistPoints.add(currPoint);
        double distanceToNextPoint, distanceCovered = 0;
        int nextIndexInPolyLine = 1;
        while(nextIndexInPolyLine<pointsOnPolyLine.size()){
            nextPoint = pointsOnPolyLine.get(nextIndexInPolyLine);
            distanceToNextPoint = GeometricUtil.distBtwInMts(currPoint, nextPoint);
            if(distanceCovered + distanceToNextPoint < contiguosDist){
                nextIndexInPolyLine++;
                distanceCovered += distanceToNextPoint;
                currPoint = nextPoint;
            } else {
                currPoint = GeometricUtil.getInterpolatedPoint(currPoint, contiguosDist - distanceCovered, nextPoint);
                equiDistPoints.add(currPoint);
                distanceCovered = 0;
            }
        }
        return equiDistPoints;
    }

}
