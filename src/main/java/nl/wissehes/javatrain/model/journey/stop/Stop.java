package nl.wissehes.javatrain.model.journey.stop;

import nl.wissehes.javatrain.model.shared.Station;

public interface Stop {
    public Station getStation();
    public Boolean getStopStatus();
}
