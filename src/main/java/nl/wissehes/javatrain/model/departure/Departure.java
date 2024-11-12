package nl.wissehes.javatrain.model.departure;

import nl.wissehes.javatrain.mapper.DepartureMapper;
import nl.wissehes.javatrain.model.NDOV.DepartureRoot;
import nl.wissehes.javatrain.model.shared.Station;

import java.util.Date;
import java.util.List;

public class Departure {

    public String journeyId;
    public String journeyDate;
    public String serviceName;
    public String lineName;
    public Boolean isCancelled;

    public Station forStation;

    public List<Station> plannedDestination;
    public List<Station> actualDestination;
    public String destinationDisplay;

    public List<Station> plannedViaStations;
    public List<Station> actualViaStations;

    public String serviceNumber;
    public String serviceType;
    public String serviceTypeCode;

    public String operator;

    public Date departureTime;
    public Date actualDepartureTime;

    /** Delay in seconds */
    public long exactDelay;
    /** Delay normalized to minutes rounded to the closest 5 */
    public long delayNormalized;

    public String plannedPlatform;
    public String actualPlatform;
    public String departureDirection;

    public List<ScheduleChange> scheduleChanges;

    public Departure() {
    }

    public static Departure fromXML(DepartureRoot rawData) {
        return new DepartureMapper(rawData).mapDeparture();
    }

}
