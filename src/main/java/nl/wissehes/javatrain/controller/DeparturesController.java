package nl.wissehes.javatrain.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import nl.wissehes.javatrain.DataStore;
import nl.wissehes.javatrain.model.response.DeparturesResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/departures")
@Tag(name = "Departures")
public class DeparturesController {

    private final DataStore dataStore;

    public DeparturesController(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @GetMapping(value = "/{station}", produces = "application/json")
    @Operation(summary = "Get the upcoming departures for a specific station", parameters = {
            @Parameter(name = "station", description = "The station code, for example 'vtn' or 'ut'", required = true)
    })
    public DeparturesResponse getDepartures(@PathVariable String station) {
        var stationData = dataStore.getStation(station).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Station not found"));

        var departures = dataStore.getDepartures()
                .stream()
                .filter(d -> d.forStation.equals(stationData))
                .sorted(Comparator.comparing(d -> d.departureTime))
                .toList();

        return new DeparturesResponse(stationData, departures);
    }

    @GetMapping(value = "/raw", produces = "application/xml")
    public String getReceivedMessagesRaw(@RequestParam(required = false) Integer index) {
        if (index != null) {
            return dataStore.getRawDepartures().get(index);
        }

        return dataStore.getRawDepartures().getLast();
    }

    @GetMapping(value = "/raw/all", produces = "application/xml")
    public List<String> getReceivedMessagesRawAll() {
        return dataStore.getRawDepartures();
    }

}
