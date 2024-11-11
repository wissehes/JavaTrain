package nl.wissehes.javatrain.model.reisinformatie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TreinVleugel {
    @JacksonXmlProperty(localName = "TreinVleugelVertrekSpoor")
    public Trein.TreinVertrekSpoor vertrekSpoor;

    @JacksonXmlProperty(localName = "PresentatieTreinVleugelVertrekSpoor")
    public LocalizedUiting presentatieVertrekSpoor;

    @JacksonXmlProperty(localName = "TreinVleugelEindBestemming")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<Trein.Eindbestemming> eindBestemming;

    @JacksonXmlProperty(localName = "PresentatieTreinVleugelEindBestemming")
    public LocalizedUiting presentatieEindBestemming;

    @JacksonXmlProperty(localName = "StopStations")
    @JacksonXmlElementWrapper(useWrapping = false)
    public Trein.Route stopStations;

    @JacksonXmlProperty(localName = "MaterieelDeelDVS")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<MaterieelDeel> materieelDelen;
}
