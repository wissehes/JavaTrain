package nl.wissehes.javatrain;

import nl.wissehes.javatrain.mapper.DepartureMapper;
import nl.wissehes.javatrain.mapper.JourneyMapper;
import nl.wissehes.javatrain.model.NDOV.DVS.DepartureDocument;
import nl.wissehes.javatrain.model.NDOV.RIT.JourneyDocument;
import nl.wissehes.javatrain.model.departure.Departure;
import nl.wissehes.javatrain.model.departure.TrainStatus;
import nl.wissehes.javatrain.model.journey.Journey;
import nl.wissehes.javatrain.model.shared.Station;
import nl.wissehes.javatrain.parser.DepartureParser;
import nl.wissehes.javatrain.parser.JourneyParser;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public final class DataStore {

    private final List<Departure> departures = new LinkedList<>();
    private final List<String> rawDepartures = new LinkedList<>();

    private final List<Journey> journeys = new LinkedList<>();
    private final List<String> rawJourneys = new LinkedList<>();

    private final List<String> rawPositions = new LinkedList<>();

    private final Map<String, Station> stations = new HashMap<>();

    private DataStore() {
    }

    /**
     * Add a departure to the data store
     * @param message
     */
    public void addDeparture(String message) {
        rawDepartures.add(message);

        DepartureDocument departureRoot = DepartureParser.parse(message);
        Departure mapped = new DepartureMapper(departureRoot).mapDeparture();
        Station station = mapped.forStation;

        // Remove any existing items with the same ID
        departures.removeIf(d ->
                d.getId().equals(mapped.getId()) ||
                        d.trainStatus.equals(TrainStatus.DEPARTED)
        );

        // Add the station
        if(!stations.containsKey(station.code)) {
            stations.put(station.code, station);
        }

        // Add the departure
        departures.add(mapped);
    }

    /**
     * Add a journey to the data store
     * @param message
     */
    public void addJourney(String message) {

        JourneyDocument journeyRoot = JourneyParser.parse(message);
        Journey mapped = new JourneyMapper(journeyRoot).mapJourney();

        // Remove any existing items with the same ID
        journeys.removeIf(j -> j.id.equals(mapped.id));

        journeys.add(mapped);
        rawJourneys.add(message);
    }

    /**
     * Add a position to the data store
     * @param message
     */
    public void addPosition(String message) {
        rawPositions.add(message);
    }

    /**
     * Get the departures
     */
    public List<Departure> getDepartures() {
        return departures;
    }

    /**
     * Get the journeys
     */
    public List<Journey> getJourneys() {
        return journeys;
    }

    /**
     * Get the raw departures
     */
    public List<String> getRawDepartures() {
        return rawDepartures;
    }

    /**
     * Get the raw journeys
     */
    public List<String> getRawJourneys() {
        return rawJourneys;
    }

    /**
     * Get the raw positions
     */
    public List<String> getRawPositions() {
        return rawPositions;
    }

    /**
     * Get all found stations
     */
    public List<Station> getStations() {
        return stations.values().stream().toList();
    }

    public Optional<Station> getStation(String code) {
        return Optional.ofNullable(
                stations.get(code.toUpperCase())
        );
    }
}
