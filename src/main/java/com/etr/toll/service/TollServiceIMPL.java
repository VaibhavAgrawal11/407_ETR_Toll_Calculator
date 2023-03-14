package com.etr.toll.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.etr.toll.model.Location;
import com.etr.toll.model.LocationDetails;
import com.etr.toll.model.LocationList;
import com.etr.toll.model.Route;
import com.etr.toll.model.TollRequest;
import com.etr.toll.model.TollResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class TollServiceIMPL implements ITollService {

	private List<Location> locations = new ArrayList<>();
	private List<LocationList> locationListReponse =  new ArrayList<>();
	private static final double TOLL_RATE_PER_KM = 0.25;

	public List<LocationList> getAllLocations(){
		try {
			locations = getLocationsFromJsonFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Location location : locations) {
			LocationList loc = new LocationList();
			loc.setCode(location.getId());
			loc.setDescription(location.getDetails().getName());
			locationListReponse.add(loc);
		}
		return locationListReponse;
	}

	public List<Location> getLocationsFromJsonFile() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = objectMapper.readTree(new File("src/main/resources/static/interchanges.json"));
		JsonNode locationsNode = rootNode.get("locations");
		Iterator<String> stringIterator = locationsNode.fieldNames();
		for (JsonNode locationNode : locationsNode) {
			String id = stringIterator.next();
			LocationDetails locationDetails = objectMapper.treeToValue (locationNode, LocationDetails.class);
			Location loc = new Location(Integer.valueOf(id), locationDetails);
			locations.add(loc);
		}
		return locations;
	}

	public TollResponse calculateToll(TollRequest request) throws IOException {
		
		locations = getLocationsFromJsonFile();
		int sourceId = request.getSourceId();
		int destinationId = request.getDestinationId();

		Location source = locations.stream()
				.filter(location -> location.getId() == sourceId)
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Invalid sorce id: " + sourceId));

		Location destination = locations.stream()
				.filter(location -> location.getId() == destinationId)
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Invalid destination id: " + destinationId));

		double distanceInKm = 0;
		distanceInKm = calculateDistanceInKm(source, destination, locations);
		
		double tollAmount = TOLL_RATE_PER_KM * distanceInKm;	

		TollResponse response = new TollResponse();
		response.setCost(tollAmount);
		response.setDestination(destination.getDetails().getName());
		response.setSource(source.getDetails().getName());
		return response;
	}

	private static double calculateDistanceInKm(Location source, Location destination, List<Location> locations) throws IOException {

		double totalDistance = 0.0;
		List<Integer> visitedLocations = new ArrayList<>();
		visitedLocations.add(source.getId());

		while (source.getId() != destination.getId()) 
		{
			Route route = source.getDetails().getRoutes().stream()
					.filter(r -> r.getToId() == destination.getId())
					.findFirst()
					.orElse(null);

			if (route != null) {
				totalDistance += route.getDistance();
				visitedLocations.add(destination.getId());
				break;
			}

			Optional<Route> optionalRoute = source.getDetails().getRoutes().stream()
					.filter(r -> !visitedLocations.contains(r.getToId()))
					.findFirst();

			if (optionalRoute.isPresent()) {
				Route route1 = optionalRoute.get();
				totalDistance += route1.getDistance();
				visitedLocations.add(route1.getToId());
				source = locations.stream()
						.filter(location -> location.getId() == route1.getToId())
						.findFirst()
						.orElseThrow(() -> new IllegalStateException("Invalid location id: " + route1.getToId()));
			} else {
				throw new IllegalArgumentException(
						"No route found from location " + source.getDetails().getName() + " to " + destination.getDetails().getName());
			}
		}

		return totalDistance;
	}



}
