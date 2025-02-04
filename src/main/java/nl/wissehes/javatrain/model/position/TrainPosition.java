package nl.wissehes.javatrain.model.position;

import java.util.Date;

public class TrainPosition {

    public String trainNumber;
    public String materialNumber;
    public Integer sequenceNumber;

    public Double longitude;
    public Double latitude;
    public Double elevation;
    public Double speed;

    public Date locationDate;
    public String source;

    public String getId() {
        String base = trainNumber + "-" + materialNumber;

        if(sequenceNumber != null) {
            return base + "-" + sequenceNumber;
        }

        return base;
    }
}
