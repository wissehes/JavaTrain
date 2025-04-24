package nl.wissehes.javatrain.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import nl.wissehes.javatrain.DataStore;
import nl.wissehes.javatrain.model.position.TrainPosition;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Controller
@RequestMapping("/positions")
@Tag(name = "Positions")
public class PositionsController {

    private final DataStore dataStore;

    public PositionsController(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @GetMapping(value = "/raw", produces = "application/xml")
    public String getRawPosition() {
        return dataStore.getRawPositions().getLast();
    }

    @GetMapping(value = "/", produces = "application/json")
    public List<TrainPosition> getPositions() {
        return dataStore.getPositions();
    }

    @QueryMapping
    public List<TrainPosition> positions() {
        return dataStore.getPositions();
    }

}
