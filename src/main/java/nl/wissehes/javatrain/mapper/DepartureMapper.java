package nl.wissehes.javatrain.mapper;

import nl.wissehes.javatrain.model.NDOV.DVS.DepartureDocument;
import nl.wissehes.javatrain.model.NDOV.DVS.DynamischeVertrekStaat;
import nl.wissehes.javatrain.model.NDOV.InfoStatus;
import nl.wissehes.javatrain.model.NDOV.Trein;
import nl.wissehes.javatrain.model.NDOV.TreinSpoor;
import nl.wissehes.javatrain.model.departure.Departure;
import nl.wissehes.javatrain.model.shared.ScheduleChange;
import nl.wissehes.javatrain.model.departure.SpecialFlags;
import nl.wissehes.javatrain.model.departure.TrainStatus;
import nl.wissehes.javatrain.model.shared.Station;

import java.time.Duration;
import java.util.Date;
import java.util.List;

public class DepartureMapper {

    DepartureDocument data;

    public DepartureMapper(DepartureDocument data) {
        this.data = data;
    }

    private Trein getTrein() {
        return data.reisinformatieProduct.dynamischeVertrekStaat.trein;
    }

    public Departure mapDeparture() {
        DynamischeVertrekStaat dvs = data.reisinformatieProduct.dynamischeVertrekStaat;
        Trein trein = getTrein();

        Departure departure = new Departure();

        departure.journeyId = dvs.ritId;
        departure.journeyDate = dvs.ritDatum;
        departure.serviceName = null; // TODO: Add service name?
        departure.lineName = trein.lijnNummer;
        departure.forStation = new Station(dvs.ritStation);
        departure.serviceNumber = trein.treinNummer;

        departure.serviceType = trein.treinSoort.value();
        departure.serviceTypeCode = trein.treinSoort.code();

        departure.plannedDestination = getDestinations(InfoStatus.GEPLAND);
        departure.actualDestination = getDestinations(InfoStatus.ACTUEEL);
        departure.destinationDisplay = trein.presentatieTreinEindBestemming.uitingen.getFirst().text();

        departure.plannedViaStations = getViaStations(InfoStatus.GEPLAND);
        departure.actualViaStations = getViaStations(InfoStatus.ACTUEEL);

        departure.operator = trein.vervoerder;

        departure.departureTime = this.getDepartureTime(InfoStatus.GEPLAND);
        departure.actualDepartureTime = this.getDepartureTime(InfoStatus.ACTUEEL);
        departure.exactDelay = Duration.parse(trein.exacteVertrekVertraging).getSeconds();
        departure.delayNormalized = Duration.parse(trein.gedempteVertrekVertraging).toMinutes();

        departure.plannedPlatform = this.getPlatform(InfoStatus.GEPLAND);
        departure.actualPlatform = this.getPlatform(InfoStatus.ACTUEEL);

        departure.departureDirection = trein.vertrekRichting;

        departure.scheduleChanges = trein.wijzigingen.stream().map(ScheduleChange::new).toList();

        departure.specialFlags = new SpecialFlags(
            trein.reserveren.toBoolean(),
            trein.toeslag.toBoolean(),
            trein.speciaalKaartje.toBoolean(),
            trein.achterBlijvenAchtersteTreinDeel.toBoolean(),
            trein.nietInstappen.toBoolean()
        );
        departure.trainStatus = TrainStatus.fromCode(trein.treinStatus);

        return departure;
    }

    /**
     * Get the departure time for a specific status
     */
    private Date getDepartureTime(InfoStatus status) {
        List<Trein.VertrekTijd> vertrekTijden = getTrein().vertrekTijd;

        return vertrekTijden.stream()
            .filter(vertrekTijd -> vertrekTijd.infoStatus() == status)
            .findFirst()
            .map(Trein.VertrekTijd::date)
            .orElse(null);
    }

    /**
     * Get the destinations for a specific status
     */
    private List<Station> getDestinations(InfoStatus status) {
        return getTrein().eindBestemming
                .stream()
                .filter(eindBestemming -> eindBestemming.InfoStatus == status)
                .map(Station::new)
                .toList();
    }

    /**
     * Get the stations in between for a specific status
     * @param status
     * @return
     */
    private List<Station> getViaStations(InfoStatus status) {
        if(getTrein().verkorteRoute == null) {
            // return emptyu list
            return List.of();
        }

        Trein.Route route = getTrein().verkorteRoute
                .stream()
                .filter(routeItem -> routeItem.infoStatus == status)
                .findFirst()
                .orElse(null);

        if(route == null) {
            // return empty list
            return List.of();
        }

        return route.stations.stream().map(Station::new).toList();
    }

    /**
     * Get the platform for a specific status
     */
    private String getPlatform(InfoStatus status) {
        if(getTrein().treinVertrekSpoor == null) {
            return null;
        }

        TreinSpoor spoor = getTrein().treinVertrekSpoor.stream()
            .filter(vertrekSpoor -> vertrekSpoor.infoStatus == status)
            .findFirst()
            .orElse(null);

        if(spoor == null) {
            return null;
        }

        return spoor.getValue();
    }
}
