package nl.wissehes.javatrain.model.reisinformatie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Station {

    @JacksonXmlProperty(localName = "StationCode")
    public String code;

    @JacksonXmlProperty(localName = "Type")
    public String type;

    @JacksonXmlProperty(localName = "UICCode")
    public String uicCode;

    @JacksonXmlProperty(localName = "KorteNaam")
    public String korteNaam;

    @JacksonXmlProperty(localName = "MiddelNaam")
    public String middelNaam;

    @JacksonXmlProperty(localName = "LangeNaam")
    public String langeNaam;

}
