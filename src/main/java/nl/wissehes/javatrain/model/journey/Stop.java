package nl.wissehes.javatrain.model.journey;

import nl.wissehes.javatrain.model.shared.ScheduleChange;
import nl.wissehes.javatrain.model.shared.Station;

import java.util.List;

public class Stop {

    public Station station;
    public Station recognizableDestination;

    public boolean stopStatus;
    public boolean doNotBoard;

    public boolean isStationAccessible;
    public boolean isAssistanceAvailable;

    public Station plannedDestination;
    public Station actualDestination;

    /** Arrival */
    public Movement arrival;

    /** Departure */
    public Movement departure;

    public List<ScheduleChange> changes;

}
