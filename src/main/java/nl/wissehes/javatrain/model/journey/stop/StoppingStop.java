package nl.wissehes.javatrain.model.journey.stop;

import nl.wissehes.javatrain.model.journey.Movement;
import nl.wissehes.javatrain.model.shared.JourneyMaterialPart;
import nl.wissehes.javatrain.model.shared.ScheduleChange;
import nl.wissehes.javatrain.model.shared.Station;

import java.util.List;

public class StoppingStop implements Stop {

    public Station station;
    public Station recognizableDestination;

    public boolean stopStatus;
    public boolean doNotBoard;

    public boolean isStationAccessible;
    public boolean isAssistanceAvailable;

    public Station plannedDestination;
    public Station actualDestination;

    public List<JourneyMaterialPart> materialParts;

    /** Arrival */
    public Movement arrival;

    /** Departure */
    public Movement departure;

    public List<ScheduleChange> changes;

    @Override
    public Station getStation() {
        return station;
    }

    @Override
    public Boolean getStopStatus() {
        return stopStatus;
    }

}
