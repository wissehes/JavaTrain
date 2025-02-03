package nl.wissehes.javatrain.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import nl.wissehes.javatrain.DataStore;
import nl.wissehes.javatrain.model.journey.Journey;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/journeys")
@Tag(name = "Journeys")
public class JourneysController {

    private final DataStore dataStore;

    public JourneysController(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    /**
     * Temporary journeys endpoint.
     * The output will be changed after I create a proper Journey response model.
     * For now, it returns the parsed XML data.
     */
    @GetMapping(value = "/{code}")
    @Operation(summary = "Get a specific journey by its code")
    public Journey getJourney(@PathVariable String code) {

        return dataStore.getJourneys().stream()
                .filter(j -> j.id.equals(code))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Journey not found"));
    }

    @GetMapping(value = "/raw/all", produces = "application/xml")
    @Operation(summary = "Get the raw XML message data of all received journeys")
    public List<String> getReceivedJourneysRawAll() {
        return dataStore.getRawJourneys();
    }

    @GetMapping(value = "/raw", produces = "application/xml")
    @Operation(summary = "Get the raw XML message data of the last received journey")
    public String getRawJourney(@RequestParam(required = false) Integer index) {
        if (index != null) {
            return dataStore.getRawJourneys().get(index);
        }

        return dataStore.getRawJourneys().getLast();
    }
}
