package nl.wissehes.javatrain.model.departure;

import nl.wissehes.javatrain.model.shared.Station;

import java.util.Date;
import java.util.List;

public class Departure {

    public String journeyId;
    public String journeyDate;
    public String serviceName;
    public String lineName;

    public Station forStation;

    public List<Station> plannedDestination;
    public List<Station> actualDestination;
    public String destinationDisplay;

    public String serviceNumber;
    public String serviceType;
    public String serviceTypeCode;

    public String operator;

    public Date departureTime;
    public Date actualDepartureTime;
    /** Delay in seconds */
    public int delay;

    public String platform;
    public String actualPlatform;
    public String departureDirection;

}
