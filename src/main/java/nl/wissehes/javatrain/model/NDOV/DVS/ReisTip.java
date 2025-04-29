package nl.wissehes.javatrain.model.NDOV.DVS;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import nl.wissehes.javatrain.model.NDOV.LocalizedUiting;
import nl.wissehes.javatrain.model.NDOV.Station;

@JsonIgnoreProperties
public class ReisTip implements DvsTip {
    @JacksonXmlProperty(localName = "ReisTipStation")
    @JacksonXmlElementWrapper(useWrapping = false)
    public Station reisTipStation;

    @JacksonXmlProperty(localName = "ReisTipCode")
    public String reisTipCode;

    @JacksonXmlProperty(localName = "PresentatieReisTip")
    public LocalizedUiting presentatieReisTip;

    @Override
    public LocalizedUiting getPresentatie() {
        return presentatieReisTip;
    }
}
