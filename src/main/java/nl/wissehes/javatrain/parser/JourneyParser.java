package nl.wissehes.javatrain.parser;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import nl.wissehes.javatrain.model.NDOV.RIT.JourneyDocument;

public class JourneyParser {
    private static final XmlMapper mapper = new XmlMapper();

    public static JourneyDocument parse(String xml) {
        try {
            return mapper.readValue(xml, JourneyDocument.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse JourneyDocument", e);
        }
    }
}
