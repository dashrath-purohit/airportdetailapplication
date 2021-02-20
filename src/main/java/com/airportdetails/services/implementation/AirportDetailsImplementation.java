package com.airportdetails.services.implementation;

import com.airportdetails.model.Airport;
import com.airportdetails.model.Runway;
import com.airportdetails.services.AirportDetails;
import com.airportdetails.services.exception.DetailsNotFoundException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * AirportDetailsImplementation class contains the implementation of Airport details interface
 */
@Service
public class AirportDetailsImplementation implements AirportDetails {

  @Autowired
  BeanLoader beanLoader;

  private static final Logger logger = LoggerFactory
      .getLogger(AirportDetailsImplementation.class);

  /**
   *The method returns top 10 countries with heghest number of airports
   * @return Map<String, Integer> for Country and number of airports present in it
   */
  @Override
  public Map<String, Integer> getCountryWithAirports()  {

    HashMap<String, Integer> airportMap = new HashMap<>();

    beanLoader.countryBean().forEach(country -> {
      airportMap.put(country.getCountryName(), (int) beanLoader.airportBean().parallelStream()
          .filter(a -> a.getCountryCode().equals(country.getCountryCode()))
          .count() );
    });
    logger.info("Map generated for countries and the number of airports in it");
    return sortByValue(airportMap);
  }

  /**
   * The sortByValue method sorts Map based on its value in descending order and
   * trims down the Map to contain only 10 elements
   * @param finalMap Original map which needs to b sorted
   * @return (Sorted and trimmed map)
   */
  public Map<String, Integer> sortByValue(Map<String, Integer> finalMap) {

    List<Entry<String, Integer>> list = new LinkedList<>(finalMap.entrySet());

    list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

    Map<String, Integer> sortedMap = new LinkedHashMap<>();
    for (Entry<String, Integer> entry : list) {
      sortedMap.put(entry.getKey(), entry.getValue());
    }

    Map<String, Integer> temp = new LinkedHashMap<>();
    int count = 0;
    for (Entry<String, Integer> entry : sortedMap.entrySet()) {
      if ((count >= 10)|| (entry.getKey().isEmpty())) {
        break;
      }
      temp.put(entry.getKey(), entry.getValue());
      count++;
    }

    logger.info("Sorting of Map successful");
    return temp;
  }


  /**
   * The method returns runway details for a given country or country code
   * @param countryNameCode country name
   * @return List<Runway> runway object
   */
  @Override
  public List<Runway> getRunwayDetails(String countryNameCode) throws DetailsNotFoundException {

    try {
      Set<String> airportref = beanLoader.airportBean().parallelStream()
          .filter(a -> {
            return beanLoader.countryBean().stream()
                .filter(c -> c.getCountryCode().equals(countryNameCode) || c.getCountryName()
                    .matches(countryNameCode) || FuzzySearch.ratio(countryNameCode.toLowerCase(), c.getCountryName().toLowerCase()) > 60)
                .findFirst().get().getCountryCode()
                .equals(a.getCountryCode());
          })
          .map(Airport::getAirportId)
          .collect(Collectors.toSet());

      logger.info("Airport references collected to get the runway details");
      return beanLoader.runwayBean().parallelStream()
          .filter(r ->
              airportref.contains(r.getAirportId()))
          .collect(Collectors.toList());
    }catch (NoSuchElementException e){
      throw new DetailsNotFoundException("No Runway details found for this country");
    }

  }

}

