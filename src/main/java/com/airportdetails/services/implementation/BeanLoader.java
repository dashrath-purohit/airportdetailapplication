package com.airportdetails.services.implementation;

import com.airportdetails.model.Airport;
import com.airportdetails.model.Country;
import com.airportdetails.model.Runway;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The class configures all the beans which convert data from csv file to their respective objects
 */
@Configuration
public class BeanLoader {


  /**
   * Bean which returns the List<country> object from the data present in countries.csv
   * @return List<Country>
   */
  @Bean
  public List<Country> countryBean() {
    try{
      String fileNameCountry = "src/main/resources/datafiles/countries.csv";

      return new CsvToBeanBuilder(new FileReader(fileNameCountry))
          .withType(Country.class)
          .build()
          .parse();
    }catch (FileNotFoundException e){
      e.printStackTrace();
      return new ArrayList<>();
    }

  }

  /**
   * Bean which returns the List<Airport> object from the data present in airports.csv
   * @return List<Airport>
   */
  @Bean
  public List<Airport> airportBean() {
    try {
      String fileNameAirport = "src/main/resources/datafiles/airports.csv";
      return new CsvToBeanBuilder(new FileReader(fileNameAirport))
          .withType(Airport.class)
          .build()
          .parse();
    }catch (FileNotFoundException e){
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  /**
   * Bean which returns the List<Runway> object from the data present in runway.csv
   * @return List<Runway>
   */
  @Bean
  public List<Runway> runwayBean() {
    try{
      String fileNameRunway = "src/main/resources/datafiles/runways.csv";

      return new CsvToBeanBuilder(new FileReader(fileNameRunway))
          .withType(Runway.class)
          .build()
          .parse();

    }catch (FileNotFoundException e){
      e.printStackTrace();
      return new ArrayList<>();
    }}
}
