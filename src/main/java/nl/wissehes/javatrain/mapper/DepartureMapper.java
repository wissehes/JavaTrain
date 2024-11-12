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
        departure.serviceName = null;
        departure.lineName = trein.lijnNummer;
        departure.forStation = new Station(dvs.ritStation);
        departure.serviceNumber = trein.treinNummer;

        departure.serviceType = trein.treinSoort.value();
        departure.serviceTypeCode = trein.treinSoort.code();

        departure.plannedDestination = getDestinations(InfoStatus.GEPLAND);
        departure.actualDestination = getDestinations(InfoStatus.ACTUEEL);
        departure.destinationDisplay = trein.presentatieTreinEindBestemming.uitingen.getFirst().text();

        departure.operator = trein.vervoerder;

        departure.departureTime = this.getDepartureTime(InfoStatus.GEPLAND);
        departure.actualDepartureTime = this.getDepartureTime(InfoStatus.ACTUEEL);
        departure.delay = 0;

        departure.platform = this.getPlatform(InfoStatus.GEPLAND);
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

    private String getPlatform(InfoStatus status) {
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
