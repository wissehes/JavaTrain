package nl.wissehes.javatrain.model.reisinformatie;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class LocalizedUiting {
    @JacksonXmlProperty(localName = "Uitingen")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<Uiting> uitingen;

    public record Uiting(
            @JacksonXmlProperty(localName = "Uiting") String text,
            @JacksonXmlProperty(localName = "Taal", isAttribute = true) String language
    ){}
}
