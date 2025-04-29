package nl.wissehes.javatrain.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import nl.wissehes.javatrain.model.NDOV.RIT.JourneyDocument;

public class JourneyParser {
    private static final ObjectMapper mapper = new XmlMapper().registerModule(new JavaTimeModule());

    public static JourneyDocument parse(String xml) {
        try {
            return mapper.readValue(xml, JourneyDocument.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse JourneyDocument", e);
        }
    }
}
