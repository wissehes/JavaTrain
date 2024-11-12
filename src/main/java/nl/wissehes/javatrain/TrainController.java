package nl.wissehes.javatrain;

import nl.wissehes.javatrain.mapper.DepartureMapper;
import nl.wissehes.javatrain.model.departure.Departure;
import nl.wissehes.javatrain.model.response.DeparturesResponse;
import nl.wissehes.javatrain.model.shared.Station;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TrainController {

    private final DataStore dataStore = DataStore.getInstance();

    @GetMapping(value = "/received", produces = "application/json")
    public Departure getReceivedMessages() {
        return dataStore.getDepartures().getLast();
    }

    @GetMapping(value = "/received/raw", produces = "application/xml")
    public String getReceivedMessagesRaw() {
        return dataStore.getRawDepartures().getLast();
    }

    @GetMapping(value = "/departures/{station}", produces = "application/json")
    public DeparturesResponse getDepartures(@PathVariable String station) {
        var stationData = dataStore.getStations()
                .stream()
                .filter(s -> s.code.equalsIgnoreCase(station))
                .findFirst()
                .orElse(null);

        var departures = dataStore.getDepartures()
                .stream()
                .filter(d -> d.forStation.equals(stationData))
                .toList();

        return new DeparturesResponse(stationData, departures);
    }

    @GetMapping(value = "/stations", produces = "application/json")
    public List<Station> getStations() {
        return dataStore.getStations();
    }

}
