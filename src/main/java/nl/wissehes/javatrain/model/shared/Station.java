package nl.wissehes.javatrain.model.shared;

public class Station {

    public String code;
    public int type;
    public int uicCode;

    public String shortName;
    public String mediumName;
    public String longName;

    public Station(String code, int type, int uicCode, String shortName, String mediumName, String longName) {
        this.code = code;
        this.type = type;
        this.uicCode = uicCode;
        this.shortName = shortName;
        this.mediumName = mediumName;
        this.longName = longName;
    }

    public Station(nl.wissehes.javatrain.model.NDOV.Station station) {
        this.code = station.code;
        this.type = Integer.parseInt(station.type);
        this.uicCode = Integer.parseInt(station.uicCode);
        this.shortName = station.korteNaam;
        this.mediumName = station.middelNaam;
        this.longName = station.langeNaam;
    }
}
