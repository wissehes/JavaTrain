package nl.wissehes.javatrain.mapper;

import nl.wissehes.javatrain.model.NDOV.InfoStatus;
import nl.wissehes.javatrain.model.NDOV.Trein;
import nl.wissehes.javatrain.model.shared.Station;

import java.util.List;

public class Shared {
    /**
     * Get the destinations for a specific status
     */
    public static Station getDestination(List<Trein.Eindbestemming> item, InfoStatus status) {
        return item
                .stream()
                .filter(eindBestemming -> eindBestemming.InfoStatus == status)
                .map(Station::new)
                .toList()
                .getFirst();
    }
}
