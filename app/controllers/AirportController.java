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
import java.util.HashMap;
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
	final Map<Integer, List<Runway>> rba;
	final Map<String, List<Airport>> abc;
	final Map<String, Country> cbc;

	@Inject
	public AirportController(final FormFactory formFactory, final Airports airports) {
		this.form = formFactory.form(AirportData.class);
		rba = airports.getRunwaysByAirport();
		abc = airports.getAirportsByCountry();
		cbc = airports.getCountriesByCode();
		this.airports = airports;
	}

	public Result index() {
		return ok(views.html.index.render());
	}

	public Result reports() {
		final Comparator<Pair<Country, Integer>> comp = Comparator.comparing(p -> p.getRight());
		return ok(views.html.reports.render(asScala(airports(comp.reversed())), asScala(airports(comp)), asScala(runways()), asScala(runwayIds())));
	}

	private List<Pair<Country, Integer>> airports(final Comparator<Pair<Country, Integer>> comp) {
		return airports.getCountries().stream().map(c -> Pair.of(c, orEmpty(abc.get(c.getCode())).size())).sorted(comp).limit(10).collect(Collectors.toList());
	}

	private List<Pair<Country, List<String>>> runways() {
		final Comparator<Pair<Country, List<String>>> comp = Comparator.comparing(p -> p.getLeft().getName());
		return airports.getCountries().stream().map(
			c -> Pair.of(
				c,
				orEmpty(abc.get(c.getCode())).stream().flatMap(
					a -> orEmpty(rba.get(a.getId())).stream().map(
						r -> Optional.ofNullable(r.getSurface()).orElse("")
					).filter(s -> !s.isEmpty())
				).distinct().collect(Collectors.toList())
			)
		).sorted(comp).collect(Collectors.toList());
	}

	private List<Pair<String, Integer>> runwayIds() {
		final Map<String, Integer> map = new HashMap<>();
		final Comparator<Pair<String, Integer>> comp = Comparator.comparing(p -> p.getRight());
		airports.getRunways().stream().forEach(
			r -> {
				final String id = r.getLe_ident();
				map.put(id, Optional.ofNullable(map.get(id)).orElse(0) + 1);
			}
		);
		return map.entrySet().stream().map(
			e -> Pair.of(e.getKey(), e.getValue())
		).sorted(comp.reversed()).limit(10).collect(Collectors.toList());
	}

	private <T> List<T> orEmpty(final List<T> list) {
		return Optional.ofNullable(list).orElse(Collections.emptyList());
	}

	public Result listAirports() {
		final Form<AirportData> boundForm = form.bindFromRequest();

		if (boundForm.hasErrors()) {
			Logger.ALogger logger = Logger.of(getClass());
			logger.error("errors = {}", boundForm.errors());
			return badRequest(views.html.listAirports.render(null, asScala(Collections.emptyList()), boundForm));
		} else {
			final AirportData data = boundForm.get();
			final Comparator<Pair<Airport, List<Runway>>> comp = Comparator.comparing(p -> p.getLeft().getName());
			final Optional<String> countryName = Optional.ofNullable(data.getCountryName()).map(c -> c.toLowerCase()).filter(c -> !c.isEmpty());
			final String countryCode = Optional.ofNullable(data.getCountryCode()).map(c -> c.toUpperCase()).filter(c -> !c.isEmpty()).orElse(countryName.map(name -> getCountryCode(name)).orElse(null));
			return ok(views.html.listAirports.render(
				Optional.ofNullable(countryCode).map(c -> cbc.get(c)).orElse(null),
				asScala(orEmpty(abc.get(countryCode)).stream().map(
					airport -> Pair.of(airport, orEmpty(rba.get(airport.getId())))
				).sorted(comp).collect(Collectors.toList())),
				boundForm)
			);
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
}
