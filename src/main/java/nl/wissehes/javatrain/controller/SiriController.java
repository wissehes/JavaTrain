package nl.wissehes.javatrain.controller;

import nl.wissehes.javatrain.DataStore;
import nl.wissehes.javatrain.model.SiriMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/siri")
public class SiriController {

    private final DataStore dataStore;

    public SiriController(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @GetMapping(path = "/messages")
    public List<SiriMessage> getMessages(@RequestParam(required = false) String topicFilter) {

        if(topicFilter != null) {
            return dataStore.getRawSiriMessages().stream()
                    .filter(message -> message.topic().toLowerCase().contains(topicFilter.toLowerCase()))
                    .toList();
        }

        return dataStore.getRawSiriMessages();
    }

    public SiriMessage getMessage(int index) {
        return dataStore.getRawSiriMessages().get(index);
    }

}
