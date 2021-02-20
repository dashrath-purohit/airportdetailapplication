package com.airportdetails.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.airportdetails.AirportdetailsApplication;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * Class to test the functionality of the Airport details application
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AirportdetailsApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "local")
class AirportdetailsControllerTests {

	@Autowired
	MockMvc mockMvc;

	/**
	 * Unit test to test the health of the application
	 * @throws Exception
	 */
	@Test
	public void healthCheckTest() throws Exception {

			mockMvc.perform(MockMvcRequestBuilders.get("/"))
					.andExpect(status().isOk())
					.andExpect(content().string("Success, welcome to airportdetails application"))
					.andReturn();
	}

	/**
	 * Method that checks if a string contains multiple substrings
	 * @param input  string
	 * @param words substrings in an array
	 * @return
	 */
	public boolean containsWords(String input, String[] words) {
		return Arrays.stream(words).allMatch(input::contains);
	}

	/**
	 * Unit test to check the hghest number of airports in given countries
	 * @throws Exception
	 */
	@Test
	public void countriesWithHighestAirportaTest() throws Exception {

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/maximum/airports"))
				.andExpect(status().isOk())
				.andReturn();

		String id = result.getResponse().getContentAsString();
		assert(containsWords(id, new String[]{"United States", "Mexico", "Russia"}));
	}

	/**
	 * Unit test to get the runway details of a given country
	 * @throws Exception
	 */
	@Test
	public void runwayDetailsTest() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/runways/ZW"))
				.andExpect(status().isOk())
				.andReturn();

		String id = result.getResponse().getContentAsString();
		assert(containsWords(id, new String[]{"246281", "246282", "246287", "246270"}));

	}

	/**
	 * Unit test to test the exception thrown by getRunwayDeatilsMethod
	 * @throws Exception
	 */
	@Test
	public void getRunwayDetailsThrowsExceptionTest() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/runways/azcv"))
				.andExpect(status().is5xxServerError())
				.andReturn();

		String id = result.getResponse().getContentAsString();
		assert(containsWords(id, new String[]{"No Runway details found for this country"}));

	}

	/**
	 * Unit test to check the page not found status code
	 * @throws Exception
	 */
	@Test
	public void pageNotFoundTest() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/runays/azcv"))
				.andExpect(status().isNotFound())
				.andReturn();
	}
}
