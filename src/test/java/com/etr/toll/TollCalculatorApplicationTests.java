package com.etr.toll;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Assert;

import com.etr.toll.exception.TollException;
import com.etr.toll.model.LocationList;
import com.etr.toll.service.TollServiceIMPL;
import com.etr.toll.utils.TollServiceConstantProvider;

@SpringBootTest
class TollCalculatorApplicationTests {

	TollServiceIMPL toll = null;

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
		} catch (TollException e) {
			e.printStackTrace();
		}
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
	
}