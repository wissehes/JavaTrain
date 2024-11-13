package nl.wissehes.javatrain.parser;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import nl.wissehes.javatrain.model.NDOV.DVS.DepartureDocument;

public class DepartureParser {
    private static final XmlMapper mapper = new XmlMapper();

    public static DepartureDocument parse(String xml) {
        try {
            return mapper.readValue(xml, DepartureDocument.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse DepartureRoot", e);
        }
    }
}
