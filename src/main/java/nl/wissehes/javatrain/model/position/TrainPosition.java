package nl.wissehes.javatrain.model.position;

import java.time.OffsetDateTime;
import java.util.Date;

public class TrainPosition {

    public String trainNumber;
    public String materialNumber;
    public Integer sequenceNumber;

    public Double longitude;
    public Double latitude;
    public Double elevation;
    public Double speed;

    public OffsetDateTime locationDate;
    public String source;

    public String getId() {
        return materialNumber;
    }
}
