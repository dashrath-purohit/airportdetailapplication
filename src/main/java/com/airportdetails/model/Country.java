package com.airportdetails.model;

import com.opencsv.bean.CsvBindByName;
import org.springframework.stereotype.Component;

/**
 * Country holds the country details in a given csv file
 */
@Component
public class Country {

  @CsvBindByName(column = "id")
  private String countryId;
  @CsvBindByName(column = "code")
  private String countryCode;
  @CsvBindByName(column = "name")
  private String countryName;


  public Country(){}

  public String getCountryCode() {
    return countryCode;
  }

  public String getCountryName() {
    return countryName;
  }


}
