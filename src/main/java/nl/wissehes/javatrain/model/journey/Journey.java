package nl.wissehes.javatrain.model.journey;

import nl.wissehes.javatrain.model.NDOV.Trein;
import nl.wissehes.javatrain.model.shared.ScheduleChange;

import java.util.List;

public class Journey {

    public String serviceNumber;
    public String date;
    public Trein.TreinSoort type;
    public String operator;
    public String lineNumber;

    public List<JourneyPart> parts;
    public List<ScheduleChange> changes;

    public boolean reservationRequired;
    public boolean supplementRequired;
    public boolean specialTicketRequired;
    public boolean includeInJourneyPlanner;

    public String getId() {
        return serviceNumber + "-" + date;
    }
}
