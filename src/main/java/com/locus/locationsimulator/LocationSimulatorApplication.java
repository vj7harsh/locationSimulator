package com.locus.locationsimulator;

import com.google.maps.GeoApiContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class LocationSimulatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocationSimulatorApplication.class, args);
	}

	@Value("${key}")
    private String key;

	@Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

	@Bean
	public GeoApiContext getContext(){
		return new GeoApiContext.Builder().apiKey(key).build();
	}

}
