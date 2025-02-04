package nl.wissehes.javatrain.model.NDOV.POS;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TreinLocation {

    @JacksonXmlProperty(localName = "TreinNummer")
    public String treinNummer;

    @JacksonXmlProperty(localName = "TreinMaterieelDelen")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<TreinMaterieelDeel> treinMaterieelDelen;

}
