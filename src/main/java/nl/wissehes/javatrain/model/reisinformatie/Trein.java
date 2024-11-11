package nl.wissehes.javatrain.model.reisinformatie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Trein {

    @JacksonXmlProperty(localName = "TreinNummer")
    public String treinNummer;

    @JacksonXmlProperty(localName = "TreinSoort")
    public TreinSoort treinSoort;

    @JacksonXmlProperty(localName = "TreinFormule")
    public String treinFormule;

    @JacksonXmlProperty(localName = "Vervoerder")
    public String vervoerder;

    @JacksonXmlProperty(localName = "TreinStatus")
    public String treinStatus;

    @JacksonXmlProperty(localName = "TreinEindBestemming")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<Eindbestemming> eindBestemming;

    @JacksonXmlProperty(localName = "VertrekTijd")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<VertrekTijd> vertrekTijd;

    @JacksonXmlProperty(localName = "ExacteVertrekVertraging")
    public String exacteVertrekVertraging;

    @JacksonXmlProperty(localName = "GedempteVertrekVertraging")
    public String gedempteVertrekVertraging;

    @JacksonXmlProperty(localName = "TreinVertrekSpoor")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<TreinVertrekSpoor> treinVertrekSpoor;

    @JacksonXmlProperty(localName = "PresentatieTreinVertrekSpoor")
    public LocalizedUiting PresentatieTreinVertrekSpoor;

    @JacksonXmlProperty(localName = "VertrekRichting")
    public String vertrekRichting;

    @JacksonXmlProperty(localName = "AfstandPerronEindKopVertrekTrein")
    public String afstandPerronEindKopVertrekTrein;

    @JacksonXmlProperty(localName = "VerkorteRoute")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<Route> verkorteRoute;

    @JacksonXmlProperty(localName = "PresentatieVerkorteRoute")
    public LocalizedUiting PresentatieVerkorteRoute;

    @JacksonXmlProperty(localName = "TreinVleugel")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<TreinVleugel> treinVleugel;

    public static class Route {
        @JacksonXmlProperty(localName = "InfoStatus", isAttribute = true)
        public InfoStatus infoStatus;

        @JacksonXmlProperty(localName = "Station")
        @JacksonXmlElementWrapper(useWrapping = false)
        public List<Station> station;
    }

    public record VertrekTijd(
            @JacksonXmlProperty(localName = "InfoStatus", isAttribute = true) InfoStatus infoStatus,
            @JacksonXmlProperty(localName = "") String date
    ){}

    public record TreinVertrekSpoor(
            @JacksonXmlProperty(localName = "InfoStatus", isAttribute = true) InfoStatus infoStatus,
            @JacksonXmlProperty(localName = "SpoorNummer") String value,
            @JacksonXmlProperty(localName = "SpoorFase") String spoorFase
    ){}

    public static class Eindbestemming extends Station {
        @JacksonXmlProperty(localName = "InfoStatus", isAttribute = true)
        public InfoStatus InfoStatus;
    }

    public record TreinSoort(
            @JacksonXmlProperty(localName = "Code", isAttribute = true) String code,
            @JacksonXmlProperty(localName = "") String value
    ){}
}