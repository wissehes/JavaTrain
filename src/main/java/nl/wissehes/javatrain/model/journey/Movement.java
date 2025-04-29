package nl.wissehes.javatrain.model.journey;

import java.time.OffsetDateTime;

/**
 * Movement information
 * Represents an arrival or departure
 */
public class Movement {
    public OffsetDateTime plannedTime;
    public OffsetDateTime actualTime;
    public String plannedPlatform;
    public String actualPlatform;
    public boolean cancelled;
    /** Delay in seconds */
    public long exactDelay;
    /** Delay normalized to minutes rounded to the closest 5 */
    public long normalizedDelay;
}
