package nl.wissehes.javatrain.model.NDOV.DVS;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import nl.wissehes.javatrain.model.NDOV.LocalizedUiting;
import nl.wissehes.javatrain.model.NDOV.Station;
import nl.wissehes.javatrain.util.StringUtils;

import java.util.List;
import java.util.Optional;

@JsonIgnoreProperties
public class ReisTip implements DvsTip {
    @JacksonXmlProperty(localName = "ReisTipStation")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<Station> reisTipStations;

    @JacksonXmlProperty(localName = "ReisTipCode")
    public Type reisTipType;

    @JacksonXmlProperty(localName = "PresentatieReisTip")
    public LocalizedUiting presentatieReisTip;

    @Override
    public Optional<String> getPresentatie() {
        String formattedStations = "Onbekend station";

        if(this.reisTipStations != null && !this.reisTipStations.isEmpty()) {
            List<String> names = this.reisTipStations
                    .stream()
                    .map(s -> s.langeNaam)
                    .toList();
            formattedStations = StringUtils.naturallyJoinStrings(names);
        }

        String data = switch (this.reisTipType) {
            case STNS -> "Stopt niet in %s";
            case STO -> "Stopt ook in %s";
            case STVA -> "Stopt vanaf %s op alle tussengelegen stations";
            case STNVA -> "Stopt vanaf %s niet op tussengelegen stations";
            case STT -> "Stopt tot %s op alle tussengelegen stations";
            case STNT -> "Stopt tot %s niet op tussengelegen stations";
            case STAL -> "Stopt op alle tussengelegen stations";
            case STN -> "Stopt niet op tussengelegen stations";
        };

        return Optional.of(data.formatted(formattedStations));
    }

    public enum Type {
        STNS,
        STO,
        STVA,
        STNVA,
        STT,
        STNT,
        STAL,
        STN;
    }
}
