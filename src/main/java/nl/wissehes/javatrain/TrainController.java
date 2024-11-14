package nl.wissehes.javatrain;

import io.swagger.v3.oas.annotations.tags.Tag;
import nl.wissehes.javatrain.model.departure.Departure;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Trains controller", description = "Used mainly for debugging purposes")
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

}
