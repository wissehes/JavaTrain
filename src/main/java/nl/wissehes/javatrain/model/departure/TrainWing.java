package nl.wissehes.javatrain.model.departure;

import nl.wissehes.javatrain.model.shared.MaterialPart;
import nl.wissehes.javatrain.model.shared.Station;

import java.util.List;

public record TrainWing(
        Station destination,
        Station actualDestination,
        String platform,
        String actualPlatform,
        List<MaterialPart> materialParts
) {
}