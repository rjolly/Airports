package services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.MappingStrategy;
import models.Country;
import models.Airport;
import models.Runway;

public class Main implements Airports {
	private final File file = new File("db.ser");
	private final String path = "https://s3-eu-west-1.amazonaws.com/lunatechassessments/";
	private final List<Country> countries = new ArrayList<>();
	private final List<Airport> airports = new ArrayList<>();
	private final List<Runway> runways = new ArrayList<>();
	private final Map<Integer, List<Runway>> runwaysByAirport = new HashMap<>();
	private final Map<String, List<Airport>> airportsByCountry = new HashMap<>();
	private final Map<String, Country> countriesByCode = new HashMap<>();

	public Main() {
		if (file.exists()) {
			load();
		} else {
			countries.addAll(get(path + "countries.csv", Country.class));
			airports.addAll(get(path + "airports.csv", Airport.class));
			runways.addAll(get(path + "runways.csv", Runway.class));
			Runtime.getRuntime().addShutdownHook(new Thread() {
				public void run() {
					store();
				}
			});
		}
		System.out.println("countries : " + countries.size());
		System.out.println("airports : " + airports.size());
		System.out.println("runways : " + runways.size());
		runways.stream().forEach(runway -> {
			final int ref = runway.getAirport_ref();
			List<Runway> list = runwaysByAirport.get(ref);
			if (list == null) {
				runwaysByAirport.put(ref, list = new ArrayList<>());
			}
			list.add(runway);
		});
		airports.stream().forEach(airport -> {
			final String code = airport.getIso_country();
			List<Airport> list = airportsByCountry.get(code);
			if (list == null) {
				airportsByCountry.put(code, list = new ArrayList<>());
			}
			list.add(airport);
		});
		countries.stream().forEach(country -> {
			countriesByCode.put(country.getCode(), country);
		});
	}

	@SuppressWarnings("unchecked")
	private <T> List<T> get(final String str, final Class<T> cls) {
		System.out.println("retrieving " + str);
		try (final Reader reader = new InputStreamReader(new URL(str).openStream(), "UTF-8")) {
			final CsvToBean c = new CsvToBeanBuilder(reader).withType(cls).build();
			final MappingStrategy s = new HeaderColumnNameMappingStrategy() {
				@Override
				public String getColumnName(int col) {
					final String s = super.getColumnName(col);
					return s == null || s.isEmpty()?"empty":s;
				}
			};
			s.setType(cls);
			c.setMappingStrategy(s);
			return c.parse();
		} catch (final MalformedURLException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void store() {
		System.out.println("writing state");
		try (final ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file))) {
			os.writeObject(countries);
			os.writeObject(airports);
			os.writeObject(runways);
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void load() {
		System.out.println("reading state");
		try (final ObjectInputStream is = new ObjectInputStream(new FileInputStream(file))) {
			countries.addAll((List<Country>) is.readObject());
			airports.addAll((List<Airport>) is.readObject());
			runways.addAll((List<Runway>) is.readObject());
		} catch (final ClassNotFoundException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	public List<Country> getCountries() {
		return countries;
	}

	public List<Airport> getAirports() {
		return airports;
	}

	public List<Runway> getRunways() {
		return runways;
	}

	public Map<Integer, List<Runway>> getRunwaysByAirport() {
		return runwaysByAirport;
	}

	public Map<String, List<Airport>> getAirportsByCountry() {
		return airportsByCountry;
	}

	public Map<String, Country> getCountriesByCode() {
		return countriesByCode;
	}

	public static void main(final String args[]) {
		new Main();
	}
}
