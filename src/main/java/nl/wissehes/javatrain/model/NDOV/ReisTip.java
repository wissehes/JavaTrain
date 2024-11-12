package nl.wissehes.javatrain.model.NDOV;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonIgnoreProperties
public class ReisTip {
    @JacksonXmlProperty(localName = "ReisTipStation")
    @JacksonXmlElementWrapper(useWrapping = false)
    public Station reisTipStation;

    @JacksonXmlProperty(localName = "ReisTipCode")
    public String reisTipCode;

    @JacksonXmlProperty(localName = "PresentatieReisTip")
    public LocalizedUiting presentatieReisTip;
}
