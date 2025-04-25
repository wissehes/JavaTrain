package nl.wissehes.javatrain.model.journey;

import java.util.Date;

/**
 * Movement information
 * Represents an arrival or departure
 */
public class Movement {
    public Date plannedTime;
    public Date actualTime;
    public String plannedPlatform;
    public String actualPlatform;
    public boolean cancelled;
    /** Delay in seconds */
    public long exactDelay;
    /** Delay normalized to minutes rounded to the closest 5 */
    public long normalizedDelay;
}
