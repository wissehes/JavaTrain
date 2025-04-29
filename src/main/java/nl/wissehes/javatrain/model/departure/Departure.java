package nl.wissehes.javatrain.model.departure;

import nl.wissehes.javatrain.mapper.DepartureMapper;
import nl.wissehes.javatrain.model.NDOV.DVS.DepartureDocument;
import nl.wissehes.javatrain.model.shared.ScheduleChange;
import nl.wissehes.javatrain.model.shared.Station;

import java.util.Date;
import java.util.List;

public class Departure {

    public String journeyId;
    public String baseJourneyId;
    public String journeyDate;
    public String serviceName;
    public String lineName;

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

    public SpecialFlags specialFlags;
    public TrainStatus trainStatus;

    public List<String> tips;
    public List<ScheduleChange> scheduleChanges;
    public List<TrainWing> wings;

    public Boolean getIsCancelled() {
        return this.scheduleChanges
                .stream()
                .anyMatch(s -> s.type.equals(ScheduleChange.ChangeType.CANCELLED_DEPARTURE));
    }

    /**
     * Get unique ID of this departure item
     */
    public String getId() {
        return this.forStation.code + "-" + this.journeyId + "-" + this.journeyDate;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Departure) {
            return ((Departure) obj).getId().equals(this.getId());
        }

        return false;
    }

    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }

    public Departure() {
    }

    public static Departure fromXML(DepartureDocument rawData) {
        return new DepartureMapper(rawData).mapDeparture();
    }

}
