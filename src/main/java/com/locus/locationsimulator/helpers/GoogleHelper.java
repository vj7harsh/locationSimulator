package com.locus.locationsimulator.helpers;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.LatLng;
import com.locus.locationsimulator.model.exceptions.GoogleException;
import org.springframework.stereotype.Component;

@Component
public class GoogleHelper {

    private final GeoApiContext geoApiContext;

    public GoogleHelper(){
        this.geoApiContext = new GeoApiContext.Builder().apiKey("AIzaSyAEQvKUVouPDENLkQlCF6AAap1Ze-6zMos").connectTimeout(1, TimeUnit.SECONDS).maxRetries(3).build();
    }

    public List<LatLng> getPolylinePoints(LatLng source, LatLng destination) throws GoogleException{
        try {
            DirectionsResult result = DirectionsApi.getDirections(geoApiContext, source.toUrlValue(), destination.toUrlValue()).await();
            return getCompletePolylinePoints(result);
        } catch (ApiException | InterruptedException | IOException e) {
            e.printStackTrace();
            throw new GoogleException("Couldn't get response from google");
        }
    }

    private List<LatLng> getCompletePolylinePoints(DirectionsResult result){
        return result.routes[0].overviewPolyline.decodePath();
    }

}
