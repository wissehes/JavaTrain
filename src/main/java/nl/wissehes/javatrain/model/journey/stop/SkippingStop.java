package nl.wissehes.javatrain.model.journey.stop;

import nl.wissehes.javatrain.model.shared.Station;

public class SkippingStop implements Stop {
    public Station station;
    public boolean stopStatus = false;

    @Override
    public Station getStation() {
        return station;
    }

    @Override
    public Boolean getStopStatus() {
        return stopStatus;
    }
}
