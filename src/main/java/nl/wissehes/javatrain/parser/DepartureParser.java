package nl.wissehes.javatrain.parser;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import nl.wissehes.javatrain.model.NDOV.DepartureRoot;

public class DepartureParser {
    private static final XmlMapper mapper = new XmlMapper();

    public static DepartureRoot parse(String xml) {
        try {
            return mapper.readValue(xml, DepartureRoot.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse DepartureRoot", e);
        }
    }
}
