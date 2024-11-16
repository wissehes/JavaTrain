package nl.wissehes.javatrain.model.NDOV.RIT.LogischeRit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LogischeRit {

    @JacksonXmlProperty(localName = "LogischeRitNummer")
    public String ritNummer;

    @JacksonXmlProperty(localName = "LogischeRitDeel")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<RitDeel> ritDelen;

}
