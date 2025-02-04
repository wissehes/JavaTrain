package nl.wissehes.javatrain.mapper;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import nl.wissehes.javatrain.model.NDOV.DVS.DepartureDocument;
import nl.wissehes.javatrain.model.NDOV.POS.PositionDocument;
import nl.wissehes.javatrain.model.NDOV.POS.TreinLocation;
import nl.wissehes.javatrain.model.NDOV.POS.TreinMaterieelDeel;
import nl.wissehes.javatrain.model.position.TrainPosition;

import java.util.List;

public class PositionsMapper {

    PositionDocument data;

    public PositionsMapper(PositionDocument data) {
        this.data = data;
    }

    public PositionsMapper(String xmlMessage) {
        XmlMapper mapper = new XmlMapper();

        try {
            this.data = mapper.readValue(xmlMessage, PositionDocument.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse PositionDocument", e);
        }
    }

    public List<TrainPosition> mapPositions() {
        return data.treinLocations
                .stream()
                .flatMap(location ->
                        location.treinMaterieelDelen
                                .stream()
                                .map(deel -> mapPosition(location, deel))
                )
                .toList();
    }

    private TrainPosition mapPosition(TreinLocation item, TreinMaterieelDeel deel) {
        var position = new TrainPosition();

        position.trainNumber = item.treinNummer;
        position.materialNumber = deel.materieelDeelNummer;
        position.sequenceNumber = deel.volgNummer;
        position.locationDate = deel.gpsDatumTijd;
        position.source = deel.bron;
        position.longitude = deel.longitude;
        position.latitude = deel.latitude;
        position.elevation = deel.elevation;
        position.speed = deel.snelheid;

        return position;
    }

}
