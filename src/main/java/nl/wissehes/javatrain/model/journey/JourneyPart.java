package nl.wissehes.javatrain.model.journey;

import nl.wissehes.javatrain.model.departure.ScheduleChange;

import java.util.List;

public class JourneyPart {

    public String serviceNumber;
    public List<Stop> stops;
    public List<ScheduleChange> changes;

}
