package nl.wissehes.javatrain.model.departure;

import nl.wissehes.javatrain.model.shared.ScheduleChange;
import nl.wissehes.javatrain.model.shared.Station;

import java.util.List;

public record TrainWing(
        Station destination,
        Station actualDestination,
        String platform,
        String actualPlatform,
        List<MaterialPart> materialParts
) {

    public record MaterialPart(
            String id,
            Station destination,
            Station actualDestination,
            String type,
            /** Length in meters */
            int length,
            int departurePosition,
            int departureOrder,
            List<ScheduleChange> scheduleChanges
    ) {
    }
}