package nl.wissehes.javatrain.mapper;

import nl.wissehes.javatrain.model.NDOV.InfoStatus;
import nl.wissehes.javatrain.model.NDOV.RIT.JourneyDocument;
import nl.wissehes.javatrain.model.NDOV.RIT.LogischeRit.DeelStation;
import nl.wissehes.javatrain.model.NDOV.RIT.LogischeRit.RitDeel;
import nl.wissehes.javatrain.model.NDOV.RIT.ReisInformatieProductRitInfo;
import nl.wissehes.javatrain.model.NDOV.RIT.RitInfo;
import nl.wissehes.javatrain.model.journey.Journey;
import nl.wissehes.javatrain.model.journey.JourneyPart;
import nl.wissehes.javatrain.model.journey.Stop;
import nl.wissehes.javatrain.model.shared.Station;

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
        part.stops = deel.stops.stream().map(this::mapJourneyPartStop).toList();
//        part.changes = deel.wijzigingen.stream().map(ScheduleChange::new).toList();
        return part;
    }

    private Stop mapJourneyPartStop(DeelStation station) {
        var stop = new Stop();

        stop.station = new Station(station.station);
        if(station.herkenbareBestemming != null) {
            stop.recognizableDestination = new Station(station.herkenbareBestemming.station());
        }

        stop.isStationAccessible = station.stationToegankelijk.toBoolean();
        stop.isAssistanceAvailable = station.stationReisAssistentie.toBoolean();

        if(station.eindBestemming != null) {
            var foundActualStation = station.eindBestemming.stream().filter(e -> e.InfoStatus == InfoStatus.ACTUEEL).findFirst();
            var foundPlannedStation = station.eindBestemming.stream().filter(e -> e.InfoStatus == InfoStatus.GEPLAND).findFirst();

            foundActualStation.ifPresent(eindbestemming -> stop.actualDestination = new Station(eindbestemming));
            foundPlannedStation.ifPresent(eindbestemming -> stop.plannedDestination = new Station(eindbestemming));
        }

        if(station.aankomstTijd != null) {
            var foundActualArrival = station.aankomstTijd.stream().filter(e -> e.infoStatus() == InfoStatus.ACTUEEL).findFirst();
            var foundPlannedArrival = station.aankomstTijd.stream().filter(e -> e.infoStatus() == InfoStatus.GEPLAND).findFirst();

            foundActualArrival.ifPresent(aankomstTijd -> stop.actualArrivalTime = aankomstTijd.date());
            foundPlannedArrival.ifPresent(aankomstTijd -> stop.plannedArrivalTime = aankomstTijd.date());
        }

        return stop;
    }
}
