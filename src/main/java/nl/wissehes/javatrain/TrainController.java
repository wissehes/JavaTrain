package nl.wissehes.javatrain;

import nl.wissehes.javatrain.mapper.DepartureMapper;
import nl.wissehes.javatrain.model.departure.Departure;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrainController {

    @GetMapping(value = "/received", produces = "application/json")
    public Departure getReceivedMessages() {
        var departure = DataReceiver.receivedMessages.getLast();

        DepartureMapper mapper = new DepartureMapper(departure);
        return mapper.mapDeparture();
    }

    @GetMapping(value = "/received/raw", produces = "application/xml")
    public String getReceivedMessagesRaw() {
        return DataReceiver.receivedMessagesRaw.getLast();
    }

}
