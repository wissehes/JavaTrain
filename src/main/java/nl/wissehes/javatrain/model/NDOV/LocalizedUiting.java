package nl.wissehes.javatrain.model.NDOV;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;
import java.util.Optional;

public class LocalizedUiting {
    @JacksonXmlProperty(localName = "Uitingen")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<Uiting> uitingen;

    public Optional<String> getForLanguage(Language language) {
        return uitingen
                .stream()
                .filter(u -> u.language.equals(language))
                .findFirst()
                .map(u -> u.text);
    }

    public record Uiting(
            @JacksonXmlProperty(localName = "Uiting") String text,
            @JacksonXmlProperty(localName = "Taal", isAttribute = true) Language language
    ){}

    public enum Language {
        nl, en
    }
}
