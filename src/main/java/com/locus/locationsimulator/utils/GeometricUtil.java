package com.locus.locationsimulator.utils;

import com.google.maps.model.LatLng;

public class GeometricUtil {

    private static final double earthRadius = 6371000; // in metres

    /*
    Implementation of Haversine formula to calculate distance between 2 points on a spherical surface
    */ 
    public static double distBtwInMts(LatLng origin, LatLng destination) {
        double latDiffInRad = Math.toRadians(destination.lat - origin.lat);
        double lngDiffInRad = Math.toRadians(destination.lng - origin.lng);
        double sineOfLatDiff = Math.sin(latDiffInRad / 2);
        double sineOfLngDIff = Math.sin(lngDiffInRad / 2);
        double a = Math.pow(sineOfLatDiff, 2) + Math.pow(sineOfLngDIff, 2)
                * Math.cos(Math.toRadians (origin.lat)) * Math.cos(Math.toRadians(destination.lat));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return earthRadius * c;
    }

    public static LatLng getInterpolatedPoint(LatLng currPoint, double interpDist, LatLng nextPoint){
        LatLng interpolatedPoint = new LatLng();
        double totalDistance = distBtwInMts(currPoint, nextPoint);
        double ratio = interpDist / totalDistance;
        interpolatedPoint.lat = currPoint.lat + ratio*(nextPoint.lat - currPoint.lat);
        interpolatedPoint.lng = currPoint.lng + ratio*(nextPoint.lng - currPoint.lng);
        return interpolatedPoint;
    }


    
}
