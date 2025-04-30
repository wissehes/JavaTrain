package nl.wissehes.javatrain.mapper;

import nl.wissehes.javatrain.model.NDOV.*;
import nl.wissehes.javatrain.model.NDOV.DVS.*;
import nl.wissehes.javatrain.model.departure.Departure;
import nl.wissehes.javatrain.model.departure.TrainWing;
import nl.wissehes.javatrain.model.shared.MaterialPart;
import nl.wissehes.javatrain.model.shared.ScheduleChange;
import nl.wissehes.javatrain.model.departure.SpecialFlags;
import nl.wissehes.javatrain.model.departure.TrainStatus;
import nl.wissehes.javatrain.model.shared.Station;
import nl.wissehes.javatrain.util.StringUtilities;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static nl.wissehes.javatrain.mapper.Shared.getDestination;

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

        departure.journeyId = trein.treinNummer;
        departure.baseJourneyId = dvs.ritId;
        departure.journeyDate = dvs.ritDatum;
        departure.serviceName = trein.treinNaam;
        departure.lineName = trein.lijnNummer;
        departure.forStation = new Station(dvs.ritStation);

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

        departure.wings = mapWings(trein.treinVleugel);
        departure.tips = mapTips(Stream.concat(trein.instapTips.stream(), trein.reisTips.stream()).toList());

        return departure;
    }

    /**
     * Get the departure time for a specific status
     */
    private OffsetDateTime getDepartureTime(InfoStatus status) {
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
     */
    private List<Station> getViaStations(InfoStatus status) {
        if(getTrein().verkorteRoute == null) {
            // return empty list
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

    /**
     * Get the platform for a specific status
     */
    private String getPlatform(List<TreinSpoor> item, InfoStatus status) {
        if(getTrein().treinVertrekSpoor == null) {
            return null;
        }

        TreinSpoor spoor = item.stream()
                .filter(vertrekSpoor -> vertrekSpoor.infoStatus == status)
                .findFirst()
                .orElse(null);

        if(spoor == null) {
            return null;
        }

        return spoor.getValue();
    }

    private List<TrainWing> mapWings(List<TreinVleugel> treinVleugels) {
        return treinVleugels.stream()
            .map(treinVleugel -> new TrainWing(
                    getDestination(treinVleugel.eindBestemming, InfoStatus.GEPLAND),
                    getDestination(treinVleugel.eindBestemming, InfoStatus.ACTUEEL),
                    getPlatform(treinVleugel.vertrekSpoor, InfoStatus.GEPLAND),
                    getPlatform(treinVleugel.vertrekSpoor, InfoStatus.ACTUEEL),
                    treinVleugel.materieelDelen.stream().map(MaterialPart::new).toList()
            )).toList();
    }

    private List<String> mapTips(List<DvsTip> tips) {
        return tips
                .stream()
                .map(i -> i.getPresentatie().orElse(null))
                .filter(Objects::nonNull)
                .toList();
    }
}
