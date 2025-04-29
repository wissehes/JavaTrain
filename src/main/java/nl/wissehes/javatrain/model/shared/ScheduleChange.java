package nl.wissehes.javatrain.model.shared;

import nl.wissehes.javatrain.model.NDOV.LocalizedUiting;
import nl.wissehes.javatrain.model.NDOV.Wijziging;

public class ScheduleChange {
    public ChangeType type;
    public int code;
    public String causeShort;
    public String causeLong;
    public Station station;

    public ScheduleChange() {
    }

    public ScheduleChange(Wijziging change) {
        this.type = ChangeType.fromCode(change.wijzigingType);
        this.code = change.wijzigingType;
        this.causeShort = change.wijzigingOorzaakKort;
        this.causeLong = change.wijzigingOorzaakLang;
        if(change.station != null) {
            this.station = new Station(change.station);
        } else {
            this.station = null;
        }
    }

    private String withCause(String description) {
        if(this.causeLong != null) {
            return description + " " + this.causeLong;
        }

        return description;
    }

    private String withStationName(String description) {
        String name = "onbekend station";

        if(this.station != null) {
            name = this.station.longName;
        }

        return withCause(description + " " + name);
    }

    public String getPresentation() {
        return switch(this.type) {
            case DELAYED_DEPARTURE -> withCause("Later vertrek");
            case DELAYED_ARRIVAL -> withCause("Latere aankomst");

            case CHANGED_DEPARTURE_TIME -> withCause("Aangepaste vertrektijd");
            case CHANGED_ARRIVAL_TIME -> withCause("Aangepaste aankomsttijd");

            case CHANGED_DEPARTURE_PLATFORM -> "Gewijzigd vertrekspoor";
            case CHANGED_ARRIVAL_PLATFORM -> "Gewijzigd aankomstspoor";

            case DEPARTURE_PLATFORM_ALLOCATED -> "Vertrekspoor toegewezen";
            case ARRIVAL_PLATFORM_ALLOCATED -> "Aankomstspoor toegewezen";

            case EXTRA_TRAIN, EXTRA_DEPARTURE, EXTRA_ARRIVAL -> withCause("Extra trein");
            case CANCELLED_TRAIN, CANCELLED_DEPARTURE, CANCELLED_ARRIVAL -> withCause("Rijdt niet");
            case CHANGED_STOP_PATTERN -> withCause("Gewijzigde dienstregeling");

            case DIVERTED -> withCause("Trein is omgeleid");
            case ROUTE_SHORTENED -> withStationName("Rijdt niet verder dan");
            case ROUTE_EXTENDED -> withStationName("Rijdt verder naar");
            case ORIGIN_ROUTE_SHORTENED -> withStationName("Begint vanaf");
            case ORIGIN_ROUTE_EXTENDED, CHANGED_ORIGIN -> withStationName("Gewijzigd beginpunt");

            case CHANGED_DESTINATION -> withStationName("Let op: rijdt naar");

            case SPRINTER_WITH_INTERCITY_STOCK -> "Rijdt met sprintermaterieel";
            case INTERCITY_WITH_SPRINTER_STOCK -> "Rijdt met intercitymaterieel";
            
            default -> null;
        };
    }

    public enum ChangeType {
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
        SPRINTER_WITH_INTERCITY_STOCK(80),
        INTERCITY_WITH_SPRINTER_STOCK(81),
        MATERIAL_ITEM_CLOSED(82),
        MATERIAL_ITEM_ADDED(83),
        MATERIAL_LEFT_BEHIND(84),
        MATERIAL_ITEM_REMOVED(85),
        OTHER(0);

        private final int code;

        ChangeType(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public static ChangeType fromCode(int code) {
            for (ChangeType mod : values()) {
                if (mod.code == code) {
                    return mod;
                }
            }
            return OTHER;
        }
    }

}
