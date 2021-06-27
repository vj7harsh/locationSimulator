package com.locus.locationsimulator.helpers;

import java.util.List;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsResult;
import com.locus.locationsimulator.model.entries.Location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class GoogleHelper {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${google.url}")
    private String url;

    @Value("${key}")
    private String key;

    @Autowired
    private GeoApiContext geoApiContext;

    public List<Location> getPathPoints(Location source, Location destination) {

        restTemplate.exchange(formReqUrl(source, destination), HttpMethod.GET, null, String.class);
        return null;
    }

    private String formReqUrl(Location source, Location destination) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url).queryParam("origin", "")
                .queryParam("destination", "").queryParam("key", key);
        return builder.toUriString();
    }

    public DirectionsResult getDirections(Location source, Location destination) {
        DirectionsResult result = null;
        try {
            result = DirectionsApi.getDirections(geoApiContext, source.getCoordinates(), destination.getCoordinates())
                    .await();
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        return result;
    }

}
