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

    @JacksonXmlProperty(localName = "StationToeganlijk")
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

    @JacksonXmlProperty(localName = "ExacteAankomstVertraging")
    public String exacteVertrekVertraging;

    @JacksonXmlProperty(localName = "GedempteAankomstVertraging")
    public String gedempteVertrekVertraging;

    @JacksonXmlProperty(localName = "TreinAankomstSpoor")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<Trein.TreinVertrekSpoor> treinAankomstSpoor;

    @JacksonXmlProperty(localName = "TreinVertrekSpoor")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<Trein.TreinVertrekSpoor> treinVertrekSpoor;

    @JacksonXmlProperty(localName = "StationnementType")
    public String stationnementType;

    @JacksonXmlProperty(localName = "MaterieelDeel")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<RitMaterieelDeel> materieelDelen;

    @JacksonXmlProperty(localName = "NietInstappen")
    public NSBoolean nietInstappen;

    @JacksonXmlProperty(localName = "TreinRangeerVolledigAf")
    public NSBoolean treinRangeerVolledigAf;

    @JacksonXmlProperty(localName = "Wijziging")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<Wijziging> wijzigingen;

    public record StopStatus(
            @JacksonXmlProperty(localName = "InfoStatus") InfoStatus infoStatus,
            @JacksonXmlProperty(localName = "") NSBoolean stopStatus
    ) {}
}
