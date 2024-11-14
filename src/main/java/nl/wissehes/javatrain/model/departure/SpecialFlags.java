package nl.wissehes.javatrain.model.departure;

public record SpecialFlags (
    Boolean reservationRequired,
    Boolean supplementRequired,
    Boolean specialTicketRequired,
    Boolean rearTrainSetRemains,
    Boolean doNotBoard
) {}