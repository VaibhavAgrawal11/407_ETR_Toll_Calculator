package com.etr.toll;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Assert;

import com.etr.toll.exception.TollException;
import com.etr.toll.exception.TollException.TollErrorType;
import com.etr.toll.model.LocationList;
import com.etr.toll.model.TollRequest;
import com.etr.toll.model.TollResponse;
import com.etr.toll.service.TollServiceIMPL;
import com.etr.toll.utils.TollServiceConstantProvider;

@SpringBootTest
class TollCalculatorApplicationTests {

	TollServiceIMPL toll = null;
	TollRequest request = new TollRequest();

	@BeforeEach
	public void setUp() {
		String file = "src/main/resources/static/test.json";
		toll = new TollServiceIMPL(file);
	}

	@Test
	public void givenLocations_WhenListCalled_ShouldProvideAllLocations(){
		int count =0;
		try {
			List<LocationList> locations = toll.getAllLocations();
			for (LocationList locationList : locations)
				count++;               
		} catch (TollException e) {}
		Assert.assertEquals(4, count);
	}

	@Test
	public void givenLocations_WhenInvalidFileProvided_ShouldThrowException() {
		String fakeLocation = "src/main/resources/static/test.json";
		toll = new TollServiceIMPL(fakeLocation);
		try {
			toll.getAllLocations();
		}
		catch (TollException e) {
			Assert.assertEquals(e.getMessage(), TollServiceConstantProvider.FILE_NOT_FOUND_ERROR_MSG);
		}
	}

	@Test
	public void givenLocations_WhenSourceAndDestinationProvided_ShouldReturnToll() throws TollException {
		request.setDestinationId(1);
		request.setSourceId(2);
		TollResponse response = toll.calculateToll(request);
		System.out.println(response.getCost());
		Assert.assertEquals(response.getCost(),1,5155);

	}

	@Test
	public void givenLocations_WhenInvalidSourceProvided_ShouldThrowException() {
		request.setDestinationId(1);
		request.setSourceId(4);
		try {
			toll.calculateToll(request);
		}catch(TollException e) {
			Assert.assertEquals(e.getMessage(),TollServiceConstantProvider.INVALID_LOCATION_ID_MSG + "4");
		}
	}
	
	@Test
	public void givenLocations_WhenInvalidDestinationProvided_ShouldThrowException() {
		request.setDestinationId(4);
		request.setSourceId(1);
		try {
			toll.calculateToll(request);
		}catch(TollException e) {
			Assert.assertEquals(e.getMessage(),TollServiceConstantProvider.INVALID_LOCATION_ID_MSG + "4");
		}
	}
	
	@Test
	public void givenLocations_WhenDestinationAndSourceAreSame_ShouldThrowException() {
		request.setDestinationId(1);
		request.setSourceId(1);
		try {
			toll.calculateToll(request);
		}catch(TollException e) {
			Assert.assertEquals(e.getMessage(),TollServiceConstantProvider.IDENTICAL_SOURCE_DESTINATION_MSG);
		}

	}
	
	@Test
	public void givenLocations_WhenNoRouteAvailable_ShouldThrowException() {
		request.setDestinationId(10);
		request.setSourceId(1);
		try {
			toll.calculateToll(request);
		}catch(TollException e) {
			Assert.assertEquals(e.getMessage(),TollServiceConstantProvider.NO_ROUTE_FOUND_MSG);
		}

	}
}