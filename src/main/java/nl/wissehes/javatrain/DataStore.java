package nl.wissehes.javatrain;

import nl.wissehes.javatrain.mapper.DepartureMapper;
import nl.wissehes.javatrain.mapper.JourneyMapper;
import nl.wissehes.javatrain.mapper.PositionsMapper;
import nl.wissehes.javatrain.model.NDOV.DVS.DepartureDocument;
import nl.wissehes.javatrain.model.NDOV.RIT.JourneyDocument;
import nl.wissehes.javatrain.model.departure.Departure;
import nl.wissehes.javatrain.model.departure.TrainStatus;
import nl.wissehes.javatrain.model.journey.Journey;
import nl.wissehes.javatrain.model.position.TrainPosition;
import nl.wissehes.javatrain.model.shared.Station;
import nl.wissehes.javatrain.parser.DepartureParser;
import nl.wissehes.javatrain.parser.JourneyParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

@Component
public final class DataStore {

    @Value("${app.datastore.store-raw-messages}")
    private boolean shouldStoreRaw;

    private final HashMap<String, Departure> departures = new HashMap<>();
    private final List<String> rawDepartures = new LinkedList<>();

    private final HashMap<String, Journey> journeys = new HashMap<>();
    private final List<String> rawJourneys = new LinkedList<>();

    private final HashMap<String, TrainPosition> positions = new HashMap<>();
    private final List<String> rawPositions = new LinkedList<>();

    private final Map<String, Station> stations = new HashMap<>();

    private DataStore() {
    }

    /**
     * Add a departure to the data store
     */
    public void addDeparture(String message) {
        if (shouldStoreRaw) {
            rawDepartures.add(message);
        }

        DepartureDocument departureRoot = DepartureParser.parse(message);
        Departure mapped = new DepartureMapper(departureRoot).mapDeparture();
        Station station = mapped.forStation;

        departures.put(mapped.getId(), mapped);

        // Save the station
        if(!stations.containsKey(station.code)) {
            stations.put(station.code, station);
        }
    }

    /**
     * Add a journey to the data store
     */
    public void addJourney(String message) {
        if(shouldStoreRaw) {
            rawJourneys.add(message);
        }

        JourneyDocument journeyRoot = JourneyParser.parse(message);
        Journey mapped = new JourneyMapper(journeyRoot).mapJourney();

        journeys.put(mapped.getId(), mapped);
    }

    /**
     * Add the received positions to the map
     */
    public void addPosition(String message) {
        if(shouldStoreRaw) {
            rawPositions.add(message);
        }

        List<TrainPosition> mappedPositions = new PositionsMapper(message).mapPositions();

        for (TrainPosition position : mappedPositions) {
            positions.put(position.trainNumber, position);
        }
    }

    /**
     * Get the departures
     */
    public List<Departure> getDepartures() {
        return new ArrayList<>(departures.values())
                .stream()
                .filter(d -> d.trainStatus.equals(TrainStatus.DEPARTED))
                .toList();
    }

    /**
     * Get the journeys
     */
    public List<Journey> getJourneys() {
        return new ArrayList<>(journeys.values());
    }

    public Journey getJourney(String id) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        return journeys.get(id + "-" + date);
    }

    /**
     * Get the positions
     */
    public List<TrainPosition> getPositions() {
        return new ArrayList<>(positions.values());
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
