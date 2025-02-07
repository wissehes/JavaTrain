package nl.wissehes.javatrain.model.NDOV.POS;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class PositionDocument {

    @JacksonXmlProperty(localName = "TreinLocation")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<TreinLocation> treinLocations;

}
