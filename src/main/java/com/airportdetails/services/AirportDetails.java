package com.airportdetails.services;

import com.airportdetails.services.exception.DetailsNotFoundException;
import com.airportdetails.model.Runway;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

/**
 * Interface that methods which return information Airports and runway in countries.
 */
public interface AirportDetails {

  /**
   * Method to return maximum number of airports in given countries
   * @return Map<String, Integer> Country and the number of airport it contains
   * @throws FileNotFoundException
   */
  Map<String, Integer> getCountryWithAirports() throws FileNotFoundException;

  /**
   * Method to return the runway details of a country given a country name or country code
   * @param countryName name of the country or country code
   * @return Runway details for the country
   * @throws FileNotFoundException
   */
  List<Runway> getRunwayDetails(String countryName) throws FileNotFoundException, DetailsNotFoundException;

}
