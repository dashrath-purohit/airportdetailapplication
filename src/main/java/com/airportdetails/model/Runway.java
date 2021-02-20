package com.airportdetails.model;

import com.opencsv.bean.CsvBindByName;
import org.springframework.stereotype.Component;


/**
 * Runway holds the runway details in a given csv file
 */
@Component
public class Runway {

  @CsvBindByName(column = "id")
  private String runwayId;
  @CsvBindByName(column = "airport_ref")
  private String airportId;
  @CsvBindByName(column = "airport_ident")
  private String airportIdentity;

  public Runway(){}

  public String getRunwayId() {
    return runwayId;
  }

  public void setRunwayId(String runwayId) {
    this.runwayId = runwayId;
  }

  public String getAirportId() {
    return airportId;
  }

  public void setAirportId(String airportId) {
    this.airportId = airportId;
  }

  public String getAirportIdentity() {
    return airportIdentity;
  }

  public void setAirportIdentity(String airportIdentity) {
    this.airportIdentity = airportIdentity;
  }

  @Override
  public String toString() {
    return "Runway{" +
        "runwayId='" + runwayId + '\'' +
        ", airportId='" + airportId + '\'' +
        ", airportIdentity='" + airportIdentity + '\'' +
        '}';
  }

}
