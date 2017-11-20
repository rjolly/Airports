package services;

import java.util.List;
import models.Country;
import models.Airport;
import models.Runway;

public interface Airports {
	List<Country> getCountries();
	List<Airport> getAirports();
	List<Runway> getRunways();
}
