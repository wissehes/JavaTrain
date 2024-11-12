package nl.wissehes.javatrain.mapper;

import nl.wissehes.javatrain.model.NDOV.DepartureRoot;
import nl.wissehes.javatrain.model.NDOV.DynamischeVertrekStaat;
import nl.wissehes.javatrain.model.NDOV.InfoStatus;
import nl.wissehes.javatrain.model.NDOV.Trein;
import nl.wissehes.javatrain.model.departure.Departure;
import nl.wissehes.javatrain.model.shared.Station;

import java.util.Date;
import java.util.List;

public class DepartureMapper {

    DepartureRoot data;

    public DepartureMapper(DepartureRoot data) {
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
        departure.isCancelled = null; // TODO: Add cancelled state

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
        departure.delay = 0;

        departure.plannedPlatform = this.getPlatform(InfoStatus.GEPLAND);
        departure.actualPlatform = this.getPlatform(InfoStatus.ACTUEEL);

        departure.departureDirection = trein.vertrekRichting;

        return departure;
    }

    /**
     * Get the departure time for a specific status
     * @param status
     * @return
     */
    private Date getDepartureTime(InfoStatus status) {
        List<Trein.VertrekTijd> vertrekTijden = getTrein().vertrekTijd;

        return vertrekTijden.stream()
            .filter(vertrekTijd -> vertrekTijd.infoStatus() == status)
            .findFirst()
            .map(Trein.VertrekTijd::date)
            .orElse(null);
    }

    private List<Station> getDestinations(InfoStatus status) {
        return getTrein().eindBestemming
                .stream()
                .filter(eindBestemming -> eindBestemming.InfoStatus == status)
                .map(Station::new)
                .toList();
    }

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

    private String getPlatform(InfoStatus status) {
        if(getTrein().treinVertrekSpoor == null) {
            return null;
        }

        Trein.TreinVertrekSpoor spoor = getTrein().treinVertrekSpoor.stream()
            .filter(vertrekSpoor -> vertrekSpoor.infoStatus() == status)
            .findFirst()
            .orElse(null);

        if(spoor == null) {
            return null;
        }

        if(spoor.spoorFase() != null) {
            return spoor.value() + spoor.spoorFase();
        } else {
            return spoor.value();
        }
    }
}
