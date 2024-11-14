package nl.wissehes.javatrain.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import nl.wissehes.javatrain.DataStore;
import nl.wissehes.javatrain.model.response.DeparturesResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;

@RestController
@RequestMapping("/departures")
@Tag(name = "Departures")
public class DeparturesController {

    private final DataStore dataStore;

    public DeparturesController(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @GetMapping(value = "/{station}", produces = "application/json")
    public DeparturesResponse getDepartures(@PathVariable String station) {
        var stationData = dataStore.getStation(station).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Station not found"));

        var departures = dataStore.getDepartures()
                .stream()
                .filter(d -> d.forStation.equals(stationData))
                .sorted(Comparator.comparing(d -> d.departureTime))
                .toList();

        return new DeparturesResponse(stationData, departures);
    }
}
