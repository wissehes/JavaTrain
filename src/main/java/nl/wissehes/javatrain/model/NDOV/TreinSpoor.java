package nl.wissehes.javatrain.model.NDOV;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TreinSpoor {
    @JacksonXmlProperty(localName = "InfoStatus", isAttribute = true)
    public InfoStatus infoStatus;

    @JacksonXmlProperty(localName = "SpoorNummer")
    public String value;

    @JacksonXmlProperty(localName = "SpoorFase")
    public String spoorFase;

    public String getValue() {
        if(this.spoorFase != null) {
            return this.value + this.spoorFase;
        } else {
            return value;
        }
    }
}
