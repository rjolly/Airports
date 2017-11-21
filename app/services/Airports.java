package services;

import java.util.List;
import java.util.Map;
import models.Country;
import models.Airport;
import models.Runway;

public interface Airports {
	List<Country> getCountries();
	List<Airport> getAirports();
	List<Runway> getRunways();
	Map<Integer, List<Runway>> getRunwaysByAirport();
	Map<String, List<Airport>> getAirportsByCountry();
}
