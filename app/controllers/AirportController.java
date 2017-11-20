package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import services.Airports;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AirportController extends Controller {

    private final Airports airports;

    @Inject
    public AirportController(final Airports airports) {
       this.airports = airports;
    }

    public Result size() {
        return ok(Integer.toString(airports.getAirports().size()));
    }
}
