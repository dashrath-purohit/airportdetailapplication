package com.airportdetails.controller;

import com.airportdetails.model.Runway;
import com.airportdetails.services.AirportDetails;
import com.airportdetails.services.exception.DetailsNotFoundException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AiportDetailsController {

  @Autowired
  AirportDetails airportDetails;

  private static final Logger logger = LoggerFactory
      .getLogger(AiportDetailsController.class);

  private static final String SUCCESS = "Success, welcome to airportdetails application";

  /**
   * Health Check endpoint
   *
   * @return SUCCESS if airport-details service is available
   */
  @GetMapping("/")
  public String getApplicationStatus() {
    return SUCCESS;
  }

  /**
   * API to return maximum number of airports in given countries
   * @return Map of countries and number of airport it contains
   * @throws FileNotFoundException
   */
  @GetMapping(value = "/maximum/airports",  produces = "application/json")
  public Map<String, Integer> getCountriesWithHighestAirport() throws FileNotFoundException {
    logger.info("Recieved request to return maximum airports in countries");
    return airportDetails.getCountryWithAirports();
  }

  /**
   * API to return the runway details of a country given a country name or country code
   * @param countryName name of the country or country code
   * @return Runway details for the country
   */
  @GetMapping(value = "/runways/{countryName}", produces = "application/json")
  public List<Runway> getRunwayDetails(@PathVariable String countryName)
      throws FileNotFoundException, DetailsNotFoundException {
    logger.info("recieved request to list all runways for country : " + countryName);
    return airportDetails.getRunwayDetails(countryName);
  }
}
