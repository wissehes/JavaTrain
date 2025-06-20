package nl.wissehes.javatrain.model.NDOV;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import nl.wissehes.javatrain.model.NDOV.DVS.InstapTip;
import nl.wissehes.javatrain.model.NDOV.DVS.ReisTip;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Trein {

    @JacksonXmlProperty(localName = "TreinNummer")
    public String treinNummer;

    @JacksonXmlProperty(localName = "LijnNummer")
    public String lijnNummer;

    @JacksonXmlProperty(localName = "TreinSoort")
    public TreinSoort treinSoort;

    @JacksonXmlProperty(localName = "TreinFormule")
    public String treinFormule;

    @JacksonXmlProperty(localName = "TreinNaam")
    public String treinNaam;

    @JacksonXmlProperty(localName = "Vervoerder")
    public String vervoerder;

    @JacksonXmlProperty(localName = "Reserveren")
    public NSBoolean reserveren;

    @JacksonXmlProperty(localName = "Toeslag")
    public NSBoolean toeslag;

    @JacksonXmlProperty(localName = "NietInstappen")
    public NSBoolean nietInstappen;

    @JacksonXmlProperty(localName = "AchterBlijvenAchtersteTreinDeel")
    public NSBoolean achterBlijvenAchtersteTreinDeel;

    @JacksonXmlProperty(localName = "RangeerBeweging")
    public NSBoolean rangeerBeweging;

    @JacksonXmlProperty(localName = "SpeciaalKaartje")
    public NSBoolean speciaalKaartje;

    @JacksonXmlProperty(localName = "TreinStatus")
    public Integer treinStatus;

    @JacksonXmlProperty(localName = "TreinEindBestemming")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<Eindbestemming> eindBestemming;

    @JacksonXmlProperty(localName = "PresentatieTreinEindBestemming")
    public LocalizedUiting presentatieTreinEindBestemming;

    @JacksonXmlProperty(localName = "VertrekTijd")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<VertrekTijd> vertrekTijd;

    @JacksonXmlProperty(localName = "ExacteVertrekVertraging")
    public String exacteVertrekVertraging;

    @JacksonXmlProperty(localName = "GedempteVertrekVertraging")
    public String gedempteVertrekVertraging;

    @JacksonXmlProperty(localName = "TreinAankomstSpoor")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<TreinSpoor> treinAankomstSpoor;

    @JacksonXmlProperty(localName = "TreinVertrekSpoor")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<TreinSpoor> treinVertrekSpoor;

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
    public List<TreinVleugel> treinVleugel = List.of();

    @JacksonXmlProperty(localName = "Wijziging")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<Wijziging> wijzigingen = List.of();

    @JacksonXmlProperty(localName = "InstapTip")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<InstapTip> instapTips = List.of();

    @JacksonXmlProperty(localName = "ReisTip")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<ReisTip> reisTips = List.of();

    public static class Route {
        @JacksonXmlProperty(localName = "InfoStatus", isAttribute = true)
        public InfoStatus infoStatus;

        @JacksonXmlProperty(localName = "Station")
        @JacksonXmlElementWrapper(useWrapping = false)
        public List<Station> stations;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record VertrekTijd(
            @JacksonXmlProperty(localName = "InfoStatus", isAttribute = true) InfoStatus infoStatus,
            @JacksonXmlProperty(localName = "") OffsetDateTime date
    ){}
    
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Eindbestemming extends Station {
        @JacksonXmlProperty(localName = "InfoStatus", isAttribute = true)
        public InfoStatus InfoStatus;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record TreinSoort(
            @JacksonXmlProperty(localName = "Code", isAttribute = true) String code,
            @JacksonXmlProperty(localName = "") String value
    ){}

}