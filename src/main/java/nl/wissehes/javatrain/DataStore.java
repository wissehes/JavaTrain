package nl.wissehes.javatrain;

import nl.wissehes.javatrain.mapper.DepartureMapper;
import nl.wissehes.javatrain.model.NDOV.DepartureRoot;
import nl.wissehes.javatrain.model.departure.Departure;
import nl.wissehes.javatrain.model.shared.Station;
import nl.wissehes.javatrain.parser.DepartureParser;

import java.util.ArrayList;
import java.util.List;

public final class DataStore {

    private static DataStore instance;

    private final List<Departure> departures = new ArrayList<>();
    private final List<String> rawDepartures = new ArrayList<>();

    private DataStore() {
    }

    public static DataStore getInstance() {
        if (instance == null) {
            instance = new DataStore();
        }
        return instance;
    }

    /**
     * Add a departure to the data store
     * @param message
     */
    public void addDeparture(String message) {
        rawDepartures.add(message);

        DepartureRoot departureRoot = DepartureParser.parse(message);
        Departure mapped = new DepartureMapper(departureRoot).mapDeparture();
        departures.add(mapped);
    }

    /**
     * Get the departures
     */
    public List<Departure> getDepartures() {
        return departures;
    }

    /**
     * Get the raw departures
     */
    public List<String> getRawDepartures() {
        return rawDepartures;
    }

    /**
     * Get all found stations
     */
    public List<Station> getStations() {
        return departures.stream()
                .map(d -> d.forStation)
                .distinct()
                .toList();
    }
}
