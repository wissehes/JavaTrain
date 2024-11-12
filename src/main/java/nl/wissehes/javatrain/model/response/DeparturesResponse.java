package nl.wissehes.javatrain.model.response;

import nl.wissehes.javatrain.model.departure.Departure;
import nl.wissehes.javatrain.model.shared.Station;

import java.util.List;

public class DeparturesResponse {
    public Station station;
    public List<Departure> departures;

    public DeparturesResponse(Station station, List<Departure> departures) {
        this.station = station;
        this.departures = departures;
    }
}
