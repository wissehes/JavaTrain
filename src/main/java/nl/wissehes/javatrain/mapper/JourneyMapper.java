package nl.wissehes.javatrain.mapper;

import nl.wissehes.javatrain.model.NDOV.*;
import nl.wissehes.javatrain.model.NDOV.RIT.JourneyDocument;
import nl.wissehes.javatrain.model.NDOV.RIT.LogischeRit.DeelStation;
import nl.wissehes.javatrain.model.NDOV.RIT.LogischeRit.RitDeel;
import nl.wissehes.javatrain.model.NDOV.RIT.ReisInformatieProductRitInfo;
import nl.wissehes.javatrain.model.NDOV.RIT.RitInfo;
import nl.wissehes.javatrain.model.journey.Journey;
import nl.wissehes.javatrain.model.journey.JourneyPart;
import nl.wissehes.javatrain.model.journey.Movement;
import nl.wissehes.javatrain.model.journey.Stop;
import nl.wissehes.javatrain.model.shared.ScheduleChange;
import nl.wissehes.javatrain.model.shared.Station;

import java.time.Duration;
import java.util.List;

public class JourneyMapper {

    JourneyDocument data;

    public JourneyMapper(JourneyDocument data) {
        this.data = data;
    }

    public Journey mapJourney() {
        var journey = new Journey();
        ReisInformatieProductRitInfo reisInformatieProduct = data.reisInformatieProduct;
        RitInfo ritInfo = reisInformatieProduct.ritInfo;

        journey.id = reisInformatieProduct.ritInfo.nummer;
        journey.date = reisInformatieProduct.ritInfo.datum;
        journey.type = reisInformatieProduct.ritInfo.soort;
        journey.operator = reisInformatieProduct.ritInfo.vervoerder;
        journey.lineNumber = reisInformatieProduct.ritInfo.lijnNummer;

        journey.parts = ritInfo.logischeRit.ritDelen
                .stream()
                .map(this::mapJourneyPart)
                .toList();

        journey.reservationRequired = reisInformatieProduct.ritInfo.reserveren.toBoolean();
        journey.supplementRequired = reisInformatieProduct.ritInfo.toeslag.toBoolean();
        journey.specialTicketRequired = reisInformatieProduct.ritInfo.speciaalKaartje.toBoolean();
        journey.includeInJourneyPlanner = reisInformatieProduct.ritInfo.reisplanner.toBoolean();
        return journey;
    }

    private JourneyPart mapJourneyPart(RitDeel deel) {
        var part = new JourneyPart();

        part.serviceNumber = deel.deelNummer;
        part.stops = deel.stops.stream()
                .filter(i -> i.stopStatus.stream().anyMatch(s -> s.stopStatus() == NSBoolean.J))
                .map(this::mapJourneyPartStop)
                .toList();
//        part.changes = deel.wijzigingen.stream().map(ScheduleChange::new).toList();
        return part;
    }

    private Stop mapJourneyPartStop(DeelStation station) {
        var stop = new Stop();

        stop.station = new Station(station.station);
        if(station.herkenbareBestemming != null) {
            stop.recognizableDestination = new Station(station.herkenbareBestemming.station());
        }

        if(station.stationToegankelijk != null) {
            stop.isStationAccessible = station.stationToegankelijk.toBoolean();
        } else stop.isStationAccessible = false;

        if(station.stationReisAssistentie != null) {
            stop.isAssistanceAvailable = station.stationReisAssistentie.toBoolean();
        } else stop.isAssistanceAvailable = false;


        if(station.wijzigingen != null) {
            stop.changes = station.wijzigingen.stream().map(ScheduleChange::new).toList();
        }

        if(station.eindBestemming != null) {
            var foundActualStation = station.eindBestemming.stream().filter(e -> e.InfoStatus == InfoStatus.ACTUEEL).findFirst();
            var foundPlannedStation = station.eindBestemming.stream().filter(e -> e.InfoStatus == InfoStatus.GEPLAND).findFirst();

            foundActualStation.ifPresent(eindbestemming -> stop.actualDestination = new Station(eindbestemming));
            foundPlannedStation.ifPresent(eindbestemming -> stop.plannedDestination = new Station(eindbestemming));
        }

        if(station.aankomstTijd != null) {
            stop.arrival = mapMovement(station.aankomstTijd, station.treinAankomstSpoor, station.exacteAankomstVertraging, station.gedempteAankomstVertraging, station.wijzigingen);
        }

        if(station.vertrekTijd != null) {
            stop.departure = mapMovement(station.vertrekTijd, station.treinVertrekSpoor, station.exacteVertrekVertraging, station.gedempteVertrekVertraging, station.wijzigingen);
        }

        if(station.wijzigingen != null) {
            stop.changes = station.wijzigingen.stream().map(ScheduleChange::new).toList();
        }

        return stop;
    }

    private Movement mapMovement(List<Trein.VertrekTijd> tijd, List<TreinSpoor> spoor, String exactDelay, String normalizedDelay, List<Wijziging> wijzigingen) {
        var movement = new Movement();

        if(tijd != null) {
            var foundActualArrival = tijd.stream().filter(e -> e.infoStatus() == InfoStatus.ACTUEEL).findFirst();
            var foundPlannedArrival = tijd.stream().filter(e -> e.infoStatus() == InfoStatus.GEPLAND).findFirst();

            foundActualArrival.ifPresent(aankomstTijd -> movement.actualTime = aankomstTijd.date());
            foundPlannedArrival.ifPresent(aankomstTijd -> movement.plannedTime = aankomstTijd.date());
        }

        if(spoor != null) {
            var foundActualPlatform = spoor.stream().filter(e -> e.infoStatus == InfoStatus.ACTUEEL).findFirst();
            var foundPlannedPlatform = spoor.stream().filter(e -> e.infoStatus == InfoStatus.GEPLAND).findFirst();

            foundActualPlatform.ifPresent(aankomstSpoor -> movement.actualPlatform = aankomstSpoor.getValue());
            foundPlannedPlatform.ifPresent(aankomstSpoor -> movement.plannedPlatform = aankomstSpoor.getValue());
        }

        if(exactDelay != null) {
            movement.exactDelay = Duration.parse(exactDelay).getSeconds();
        }

        if(normalizedDelay != null) {
            movement.normalizedDelay = Duration.parse(normalizedDelay).toMinutes();
        }

        if(wijzigingen != null && wijzigingen.stream().anyMatch(w -> w.wijzigingType == 32)) {
            movement.cancelled = true;
        }

        return movement;
    }
}
