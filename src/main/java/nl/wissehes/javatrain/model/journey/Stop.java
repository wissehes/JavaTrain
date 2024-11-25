package nl.wissehes.javatrain.model.journey;

import nl.wissehes.javatrain.model.shared.Station;

public class Stop {

    public Station station;
    public Station recognizableDestination;

    public boolean isStationAccessible;
    public boolean isAssistanceAvailable;

    public Station plannedDestination;
    public Station actualDestination;

    /** Arrivals */

    public String plannedArrivalTime;
    public String actualArrivalTime;
    public String plannedArrivalPlatform;
    public String actualArrivalPlatform;
    public boolean arrivalCancelled;
    /** Delay in seconds */
    public long exactArrivalDelay;
    /** Delay normalized to minutes rounded to the closest 5 */
    public long normalizedArrivalDelay;

    /** Departures */

    public String plannedDepartureTime;
    public String actualDepartureTime;
    public String plannedDeparturePlatform;
    public String actualDeparturePlatform;
    public boolean departureCancelled;
    /** Delay in seconds */
    public long exactDepartureDelay;
    /** Delay normalized to minutes rounded to the closest 5 */
    public long normalizedDepartureDelay;
}
