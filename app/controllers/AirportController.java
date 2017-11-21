package controllers;

import play.Logger;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import services.Airports;
import models.Airport;
import models.Runway;

import java.util.Map;
import java.util.List;
import java.util.Collections;
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

	public Result listAirports() {
		final Form<AirportData> boundForm = form.bindFromRequest();

		if (boundForm.hasErrors()) {
			Logger.ALogger logger = Logger.of(getClass());
			logger.error("errors = {}", boundForm.errors());
			return badRequest(views.html.listAirports.render(asScala(Collections.emptyList()), boundForm));
		} else {
			final AirportData data = boundForm.get();
			final String countryCode = data.getCountryCode();
			final Map<Integer, List<Runway>> rba = airports.getRunwaysByAirport();
			final Map<String, List<Airport>> abc = airports.getAirportsByCountry();
			return ok(views.html.listAirports.render(asScala(abc.containsKey(countryCode)?abc.get(countryCode).stream().map(airport -> Pair.of(airport, rba.get(airport.id))).collect(Collectors.toList()):Collections.emptyList()), boundForm));
		}
	}

	public Result size() {
		return ok(Integer.toString(airports.getAirports().size()));
	}
}
