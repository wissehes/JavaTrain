package nl.wissehes.javatrain.model.NDOV.RIT.LogischeRit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import nl.wissehes.javatrain.model.NDOV.Wijziging;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RitDeel {

    @JacksonXmlProperty(localName = "LogischeRitDeelNummer")
    public String deelNummer;

    @JacksonXmlProperty(localName = "LogischeRitDeelStation")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<DeelStation> stops;

    @JacksonXmlProperty(localName = "Wijziging")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<Wijziging> wijzigingen = List.of();
}
