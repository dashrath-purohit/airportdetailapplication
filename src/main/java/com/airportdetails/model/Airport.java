package com.airportdetails.model;

import com.opencsv.bean.CsvBindByName;
import org.springframework.stereotype.Component;

/**
 * Airport holds the airport details in a given csv file
 */
@Component
public class Airport {

  @CsvBindByName(column = "id")
  private String airportId;
  @CsvBindByName(column = "iso_country")
  private String countryCode;

  public Airport(){}

  public String getAirportId() {
    return airportId;
  }

  public String getCountryCode() {
    return countryCode;
  }

}
