package nl.wissehes.javatrain.model.departure;

import nl.wissehes.javatrain.model.NDOV.Wijziging;
import nl.wissehes.javatrain.model.shared.Station;

public class ScheduleChange {
    public ScheduleChangeType type;
    public String shortDescription;
    public String longDescription;
    public Station station;

    public ScheduleChange() {
    }

    public ScheduleChange(Wijziging change) {
        this.type = ScheduleChangeType.fromCode(change.wijzigingType);
        this.shortDescription = change.wijzigingOorzaakKort;
        this.longDescription = change.wijzigingOorzaakLang;
        this.station = null;
    }

    public enum ScheduleChangeType {
        DELAYED_DEPARTURE(10),
        DELAYED_ARRIVAL(11),
        CHANGED_DEPARTURE_TIME(12),
        CHANGED_ARRIVAL_TIME(13),
        CHANGED_DEPARTURE_PLATFORM(20),
        CHANGED_ARRIVAL_PLATFORM(21),
        DEPARTURE_PLATFORM_ALLOCATED(22),
        ARRIVAL_PLATFORM_ALLOCATED(23),
        EXTRA_TRAIN(24),
        CANCELLED_TRAIN(25),
        CHANGED_STOP_PATTERN(30),
        EXTRA_DEPARTURE(31),
        CANCELLED_DEPARTURE(32),
        DIVERTED(33),
        ROUTE_SHORTENED(34),
        ROUTE_EXTENDED(35),
        ORIGIN_ROUTE_SHORTENED(36),
        ORIGIN_ROUTE_EXTENDED(37),
        EXTRA_ARRIVAL(38),
        CANCELLED_ARRIVAL(39),
        STATUS_CHANGE(40),
        CHANGED_DESTINATION(41),
        CHANGED_ORIGIN(42),
        OTHER(0);

        private final int code;

        ScheduleChangeType(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public static ScheduleChangeType fromCode(int code) {
            for (ScheduleChangeType mod : values()) {
                if (mod.code == code) {
                    return mod;
                }
            }
            return OTHER;
        }
    }

}
