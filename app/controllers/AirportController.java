package controllers;

import play.Logger;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import services.Airports;

import models.Country;
import models.Airport;
import models.Runway;

import java.util.Map;
import java.util.List;
import java.util.Optional;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.apache.commons.lang3.tuple.Pair;

import static play.libs.Scala.asScala;

@Singleton
public class AirportController extends Controller {
	private final Form<AirportData> form;
	private final Airports airports;

	@Inject
	public AirportController(final FormFactory formFactory, final Airports airports) {
		this.form = formFactory.form(AirportData.class);
		this.airports = airports;
	}

	public Result index() {
		return ok(views.html.index.render());
	}

	public Result reports() {
		final Comparator<Pair<Country, Integer>> comp = Comparator.comparing(p -> p.getRight());
		return ok(views.html.reports.render(asScala(getList(comp.reversed())), asScala(getList(comp))));
	}

	private List<Pair<Country, Integer>> getList(final Comparator<Pair<Country, Integer>> comp) {
		final Map<String, Country> cbc = airports.getCountriesByCode();
		return airports.getAirportsByCountry().entrySet().stream().map(entry -> Pair.of(cbc.get(entry.getKey()), entry.getValue().size())).sorted(comp).limit(10).collect(Collectors.toList());
	}

	public Result listAirports() {
		final Form<AirportData> boundForm = form.bindFromRequest();

		if (boundForm.hasErrors()) {
			Logger.ALogger logger = Logger.of(getClass());
			logger.error("errors = {}", boundForm.errors());
			return badRequest(views.html.listAirports.render(null, asScala(Collections.emptyList()), boundForm));
		} else {
			final AirportData data = boundForm.get();
			final Optional<String> countryName = Optional.ofNullable(data.getCountryName()).map(c -> c.toLowerCase()).filter(c -> !c.isEmpty());
			final String countryCode = Optional.ofNullable(data.getCountryCode()).map(c -> c.toUpperCase()).filter(c -> !c.isEmpty()).orElse(countryName.map(name -> getCountryCode(name)).orElse(null));
			final Map<Integer, List<Runway>> rba = airports.getRunwaysByAirport();
			final Map<String, List<Airport>> abc = airports.getAirportsByCountry();
			final Map<String, Country> cbc = airports.getCountriesByCode();
			return ok(views.html.listAirports.render(Optional.ofNullable(countryCode).map(c -> cbc.get(c)).orElse(null), asScala(abc.containsKey(countryCode)?abc.get(countryCode).stream().map(airport -> Pair.of(airport, rba.get(airport.id))).collect(Collectors.toList()):Collections.emptyList()), boundForm));
		}
	}

	private String getCountryCode(final String countryName) {
		for (final Country country : airports.getCountries()) {
			if (country.getName().toLowerCase().startsWith(countryName)) {
				return country.getCode();
			}
		}
		return null;
	}

	public Result size() {
		return ok(Integer.toString(airports.getAirports().size()));
	}
}
