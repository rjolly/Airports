package controllers;

import play.Logger;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import services.Airports;

import java.util.Collections;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;

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
            return ok(views.html.listAirports.render(asScala(airports.getAirports().stream().filter(airport -> airport.getIso_country().equals(data.getCountryCode())).collect(Collectors.toList())), boundForm));
        }
    }

    public Result size() {
        return ok(Integer.toString(airports.getAirports().size()));
    }
}
