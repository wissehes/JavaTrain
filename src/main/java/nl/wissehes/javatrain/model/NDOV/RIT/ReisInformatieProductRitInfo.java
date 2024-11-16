package nl.wissehes.javatrain.model.NDOV.RIT;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReisInformatieProductRitInfo {

    @JacksonXmlProperty(localName = "Versie", isAttribute = true)
    String versie;

    @JacksonXmlProperty(localName = "TimeStamp", isAttribute = true)
    Date timeStamp;

    @JacksonXmlProperty(localName = "RitInfo")
    RitInfo ritInfo;
}
