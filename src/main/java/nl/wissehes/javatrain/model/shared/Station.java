package nl.wissehes.javatrain.model.shared;

import nl.wissehes.javatrain.model.departure.ScheduleChange;

public class Station {

    public String code;
    public StationType type;
    public int uicCode;

    public String shortName;
    public String mediumName;
    public String longName;

    @Override
    public boolean equals(Object obj) {

        if(obj instanceof Station) {
            return ((Station)obj).code.equals(this.code);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }

    public Station(String code, int type, int uicCode, String shortName, String mediumName, String longName) {
        this.code = code;
        this.type = StationType.fromCode(type);
        this.uicCode = uicCode;
        this.shortName = shortName;
        this.mediumName = mediumName;
        this.longName = longName;
    }

    public Station(nl.wissehes.javatrain.model.NDOV.Station station) {
        this.code = station.code;
        this.type = StationType.fromCode(Integer.parseInt(station.type));
        this.uicCode = Integer.parseInt(station.uicCode);
        this.shortName = station.korteNaam;
        this.mediumName = station.middelNaam;
        this.longName = station.langeNaam;
    }

    public enum StationType {
        LOCAL_STATION(0),
        LOCAL_STATION_JUNCTION(1),
        EXPRESS_STATION(2),
        EXPRESS_STATION_JUNCTION(3),
        INTERCITY_STATION(4),
        INTERCITY_STATION_JUNCTION(5),
        MEGA_STATION(6),
        FACULTATIVE_STATION(7);

        private final int code;

        StationType(int code) {
            this.code = code;
        }

        public static StationType fromCode(int code) {
            for (StationType mod : values()) {
                if (mod.code == code) {
                    return mod;
                }
            }
            return FACULTATIVE_STATION;
        }

        public int getCode() {
            return code;
        }
    }
}
