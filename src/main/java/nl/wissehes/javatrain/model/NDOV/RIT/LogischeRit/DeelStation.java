package nl.wissehes.javatrain.model.NDOV.RIT.LogischeRit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import nl.wissehes.javatrain.model.NDOV.*;
import nl.wissehes.javatrain.model.NDOV.RIT.RitMaterieelDeel;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeelStation {

    @JacksonXmlProperty(localName = "Station")
    public Station station;

    @JacksonXmlProperty(localName = "StationToegankelijk")
    public NSBoolean stationToegankelijk;

    @JacksonXmlProperty(localName = "PresentatieStationToegankelijk")
    public LocalizedUiting presentatieStationToegankelijk;

    @JacksonXmlProperty(localName = "StationReisAssistentie")
    public NSBoolean stationReisAssistentie;

    @JacksonXmlProperty(localName = "PresentatieStationReisAssistentie")
    public LocalizedUiting presentatieStationReisAssistentie;

    @JacksonXmlProperty(localName = "TreinEindBestemming")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<Trein.Eindbestemming> eindBestemming;

    @JacksonXmlProperty(localName = "HerkenbareBestemming")
    public HerkenbareBestemming herkenbareBestemming;

    @JacksonXmlProperty(localName = "PresentatieTreinEindBestemming")
    public LocalizedUiting presentatieTreinEindBestemming;

    @JacksonXmlProperty(localName = "Stopt")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<StopStatus> stopStatus;

    @JacksonXmlProperty(localName = "AankomstTijd")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<Trein.VertrekTijd> aankomstTijd;

    @JacksonXmlProperty(localName = "ExacteAankomstVertraging")
    public String exacteAankomstVertraging;

    @JacksonXmlProperty(localName = "GedempteAankomstVertraging")
    public String gedempteAankomstVertraging;

    @JacksonXmlProperty(localName = "VertrekTijd")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<Trein.VertrekTijd> vertrekTijd;

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

    @JacksonXmlProperty(localName = "StationnementType")
    public String stationnementType;

    @JacksonXmlProperty(localName = "MaterieelDeel")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<RitMaterieelDeel> materieelDelen;

    @JacksonXmlProperty(localName = "NietInstappen")
    public NSBoolean nietInstappen;

    @JacksonXmlProperty(localName = "TreinRangeerVolledigAf")
    public NSBoolean treinRangeerVolledigAf;

    @JacksonXmlProperty(localName = "MaterieelWijziging")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<MaterieelWijziging> materieelWijziging = List.of();

    @JacksonXmlProperty(localName = "Wijziging")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<Wijziging> wijzigingen;

    public record StopStatus(
            @JacksonXmlProperty(localName = "InfoStatus") InfoStatus infoStatus,
            @JacksonXmlProperty(localName = "") NSBoolean stopStatus
    ) {}

    public record HerkenbareBestemming(
            @JacksonXmlProperty(localName = "Station") Station station,
            @JacksonXmlProperty(localName = "PresentatieHerkenbareBestemming") LocalizedUiting presentatie
    ) {}
}
