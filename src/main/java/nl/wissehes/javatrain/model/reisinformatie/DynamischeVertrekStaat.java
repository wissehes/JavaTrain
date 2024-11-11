package nl.wissehes.javatrain.model.reisinformatie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DynamischeVertrekStaat {

    @JacksonXmlProperty(localName = "RitId")
    public String ritId;

    @JacksonXmlProperty(localName = "RitStation")
    public Station ritStation;

    @JacksonXmlProperty(localName = "RitDatum")
    public String ritDatum;

    @JacksonXmlProperty(localName = "Trein")
    public Trein trein;

}
