package nl.wissehes.javatrain.model.departure;

public enum TrainStatus {
    UNKNOWN(0),
    APPROACHING(1),
    INCOMING(2),
    DOORS_OPENED(3),
    DOORS_CLOSED(4),
    DEPARTED(5);

    private final int code;

    TrainStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static TrainStatus fromCode(int code) {
        for(TrainStatus status : TrainStatus.values()) {
            if(status.code == code) {
                return status;
            }
        }

        return UNKNOWN;
    }
}
