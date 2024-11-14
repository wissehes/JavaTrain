package nl.wissehes.javatrain;

import nl.wissehes.javatrain.model.departure.Departure;
import nl.wissehes.javatrain.model.shared.Station;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController
public class TrainController {

    private final DataStore dataStore;

    public TrainController(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @GetMapping(value = "/received", produces = "application/json")
    public Departure getReceivedMessages() {
        return dataStore.getDepartures().getLast();
    }

    @GetMapping(value = "/received/raw", produces = "application/xml")
    public String getReceivedMessagesRaw(@RequestParam(required = false) Integer index) {
        if (index != null) {
            return dataStore.getRawDepartures().get(index);
        }

        return dataStore.getRawDepartures().getLast();
    }

    @GetMapping(value = "/received/raw/all", produces = "application/xml")
    public List<String> getReceivedMessagesRawAll() {
        return dataStore.getRawDepartures();
    }

    @GetMapping(value = "/stations", produces = "application/json")
    public List<Station> getStations() {
        return dataStore.getStations()
                .stream()
                .sorted(Comparator.comparing(s -> s.longName))
                .toList();
    }

}
