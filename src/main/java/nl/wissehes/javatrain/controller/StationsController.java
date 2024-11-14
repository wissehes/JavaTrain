package nl.wissehes.javatrain.controller;

import nl.wissehes.javatrain.DataStore;
import nl.wissehes.javatrain.model.shared.Station;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/stations")
public class StationsController {

    private final DataStore dataStore;

    public StationsController(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @GetMapping(value = { "", "/" })
    public List<Station> getStations() {
        return dataStore.getStations()
                .stream()
                .sorted(Comparator.comparing(s -> s.longName))
                .toList();
    }

    @GetMapping(value = "/code/{code}")
    public Station getStationByCode(@PathVariable String code) {
        return dataStore.getStation(code).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Station not found"));
    }

}
